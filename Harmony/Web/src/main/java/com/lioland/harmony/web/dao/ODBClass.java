/*
 * Copyright (C) 2014 Chandima
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.lioland.harmony.web.dao;

import com.lioland.harmony.web.util.Utils;
import com.orientechnologies.orient.core.db.record.ODatabaseRecord;
import com.orientechnologies.orient.core.id.ORecordId;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.PropertyUtils;

/**
 *
 * @author Chandima
 */
public abstract class ODBClass {

    private String rid;

    public abstract String getUniqueFieldName();

    public String getEncodedRid() {
        try {
            return Utils.urlEncode(rid);
        } catch (UnsupportedEncodingException ex) {
            return null;
        }
    }

    public void loadObject() {
        Class cls = this.getClass();
        System.out.println("loading object: " + cls);
        try {
            String docClass = cls.getSimpleName();
            String query = "select * from " + docClass + " where " + getUniqueFieldName() + "='" + PropertyUtils.getProperty(this, getUniqueFieldName()) + "'";
            System.out.println("Query: " + query);
            List<ODocument> docs;
            try (ODatabaseRecord db = DBFactory.getDb()) {
                OSchema schema = db.getMetadata().getSchema();
                if (!schema.existsClass(docClass)) {
                    System.out.println("::::::::::::::WARNING::::::::::::::::");
                    System.out.println("Created missing class: " + docClass);
                    schema.createClass(docClass);
                }
                docs = db.query(new OSQLSynchQuery<ODocument>(query));
            }
            if (!docs.isEmpty()) {
                fillObject(docs.get(0), this);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException ex) {
            Logger.getLogger(ODBClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ODBClass(String rid) {
        this.rid = rid;
        ODocument doc = getODocument(rid);
        transform(doc, this.getClass());
    }

    public ODBClass() {
    }

    public void save() {
        System.out.println("Saving...");
        ODBClass odbc = this;
        ODocument doc = createODocument(odbc);
        doc.save();
        odbc.setRid(doc.getIdentity().toString());
    }

    private ODocument createODocument(ODBClass odbc) {
        System.out.println("Reading " + odbc.getClass() + "...");
        System.out.println(odbc);
        ODocument doc;
        try (ODatabaseRecord db = DBFactory.getDb()) {
            Class cls = odbc.getClass();
            if (odbc.rid == null) {
                doc = new ODocument(cls.getSimpleName());
            } else {
                System.out.println("retrieving document: " + odbc.rid);
                doc = getODocument(odbc.rid);
            }
            for (Field field : cls.getDeclaredFields()) {
                try {
                    String fieldName = field.getName();
                    Object fieldValue = PropertyUtils.getProperty(odbc, fieldName);
                    System.out.println("Setting field: " + fieldName);
                    if (fieldValue instanceof List) {
                        System.out.println("Found list");
                        List list = (List) fieldValue;
                        System.out.println("Size: " + list.size());
                        List<ODocument> linkList = new ArrayList<>();
                        if (!list.isEmpty()) {
                            for (Object ob : list) {
                                if (ob instanceof ODBClass) {
                                    ODBClass od = (ODBClass) ob;
                                    od.loadObject();
                                    linkList.add(createODocument(od));
                                    fieldValue = linkList;
                                }
                            }
                        }
                    }
                    if (fieldValue instanceof ODBClass) {
                        System.out.println("Found ODB Class");
                        ODBClass odb = (ODBClass) fieldValue;
                        odb.loadObject();
                        fieldValue = createODocument(odb);
                    }
                    if (fieldValue != null) {
                        doc.field(fieldName, fieldValue);
                    }
                } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex) {
                    Logger.getLogger(ODBClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return doc;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public static Object querySingle(String query, Class cls) {
        List results = queryList(query, cls);
        return results.get(0);
    }

    public static List queryList(String query, Class cls) {
        System.out.println("Query: " + query);
        List<ODocument> docs;
        try (ODatabaseRecord db = DBFactory.getDb()) {
            docs = db.query(new OSQLSynchQuery<ODocument>(query));
        }
        List results = new ArrayList();
        for (ODocument doc : docs) {
            Object ob = transform(doc, cls);
            results.add(ob);
        }
        return results;
    }

    private static Object transform(ODocument doc, Class cls) {
        Object ob = null;
        try {
            ob = cls.newInstance();
            fillObject(doc, ob);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException ex) {
            Logger.getLogger(ODBClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ob;
    }

    private static void fillObject(ODocument doc, Object ob) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        ODBClass odbc = (ODBClass) ob;
        odbc.setRid(doc.getIdentity().toString());

        for (String fieldName : doc.fieldNames()) {
            Object fieldValue = doc.field(fieldName);
            System.out.println("fieldName: " + fieldName);
            if (fieldValue instanceof ODocument) {
                System.out.println("ODocument found");
                fieldValue = transform((ODocument) fieldValue, Class.forName(PACKAGE_PREFIX + ((ODocument) fieldValue).getClassName()));
            } else if (fieldValue instanceof List) {
                List<ODocument> list = (List) fieldValue;
                List newList = new ArrayList();
                for (Object item : list) {
                    if (item instanceof ODocument) {
                        ODocument odoc = (ODocument) item;
                        newList.add(transform(odoc, Class.forName(PACKAGE_PREFIX + odoc.getClassName())));
                    } else {
                        newList.add(item);
                    }
                }
                fieldValue = newList;
            }
            if (fieldValue != null) {
                PropertyUtils.setProperty(ob, fieldName, fieldValue);
            }
        }
    }

    public void remove() {
        try (ODatabaseRecord db = DBFactory.getDb()) {
            db.delete(new ORecordId(rid));
        }
    }

    private ODocument getODocument(String rid) {
        ODocument doc;
        try (ODatabaseRecord db = DBFactory.getDb()) {
            doc = db.getRecord(new ORecordId(rid));
            System.out.println(rid);
        }
        return doc;
    }

    private static final String PACKAGE_PREFIX = "com.lioland.harmony.web.dao.";

}

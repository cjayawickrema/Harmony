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
package com.lioland.harmony.web.util;

import com.lioland.harmony.web.dao.DBFactory;
import com.orientechnologies.orient.core.db.record.ODatabaseRecord;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Chandima
 */
public class Utils {

    public static String urlEncode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, "UTF-8");
    }

    public static List<Map> queryList(String query) {
        System.out.println("Query: " + query);
        List<ODocument> docs;
        try (ODatabaseRecord db = DBFactory.getDb()) {
            docs = db.query(new OSQLSynchQuery<ODocument>(query));
        }
        List results = new ArrayList();
        for (ODocument doc : docs) {
            Map map = new HashMap();
            for (String fieldName : doc.fieldNames()) {
                map.put(fieldName, doc.field(fieldName));
            }
            results.add(map);
        }
        return results;
    }
}

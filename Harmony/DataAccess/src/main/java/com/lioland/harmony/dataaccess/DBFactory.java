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
package com.lioland.harmony.dataaccess;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

/**
 *
 * @author Chandima
 */
public class DBFactory {

    private ODatabaseDocumentTx db;

    private DBFactory() {
        db = new ODatabaseDocumentTx("remote:localhost/Harmony").open("root", "root");
    }

    public static DBFactory getInstance() {
        return DBFactoryHolder.INSTANCE;
    }

    public ODatabaseDocumentTx getDb() {
        return db;
    }

    public void close() {
        db.close();
    }

    private static class DBFactoryHolder {

        private static final DBFactory INSTANCE = new DBFactory();
    }
}

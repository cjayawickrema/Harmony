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
package com.lioland.harmony.services.dao;

import java.util.List;

/**
 *
 * @author Chandima
 */
public class Department extends ODBClass {

    private String code;
    private String name;

    public Department(String rid) {
        super(rid);
    }

    public Department() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" + "code=" + code + ", name=" + name + '}';
    }

    public List<Employee> getEmployees() {
        return queryList("select * from Employee where department.@rid='" + getRid() + "'", Employee.class);
    }

    @Override
    public String getUniqueFieldName() {
        return "code";
    }

}

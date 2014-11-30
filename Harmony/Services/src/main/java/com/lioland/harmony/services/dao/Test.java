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


public class Test {

    public static void main(String[] args) {
        DBFactory factory = DBFactory.getInstance();
//        Department department = new Department("#13:6");
//        department.setCode("111");
//        department.setName("GTO");
//
//        Employee e = new Employee();
//        e.setDepartment(department);
//        e.setName("B");
//        e.setAddress("Maharagama");
//        e.setAge(26);
//        e.setDateOfBirth(new Date());
//        e.setNumber("1234");
//        e.save();
//        System.out.println(e.getRid());

//        factory.getDb().getRecord(OIdentifiable);
        
//        User user = new User();
//        user.setDateOfBirth(new Date());
//        user.setEmail("chandima@lioland.com");
//        user.setFirstName("Chandima");
//        user.setJoinedDate(new Date());
//        user.setLastName("Jayawickrema");
//        user.setPassword("chandima");
//        user.save();
        
        User user = new User();
        user.setEmail("chandima@lioland.com");
        user.loadObject();
//        
        System.out.println(user.getLastName());
        
        factory.close();
    }
}

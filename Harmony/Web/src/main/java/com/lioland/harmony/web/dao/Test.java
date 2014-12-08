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

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        tags();
//        needs();
    }

    public static void needs() {

        Need n = new Need();
        n.setAddress("ssss");
        n.setCity("Maha");
        n.setCountry("Sri Lanka");
        n.setDescription("kjhgsfkjs dg sfkjgs f4");
        n.setLatitude(0);
        n.setLongtitude(0);
        User u = new User();
        u.setEmail("chandima@lioland.com");
        n.setReporter(u);
        n.setState("Western");
        n.setTitle("Test al ahd");
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(new Tag("Education"));
        tags.add(new Tag("Medication"));
        n.setTags(tags);
        n.save();
    }

    public static void tags() {
        String[] s = {"Educational", "Food", "Water", "Construction", "Clothes", "Medication", "Organs", "Blood", "Other"};
        for (String s1 : s) {
            Tag tag = new Tag(s1);
            tag.setDescription(s1);
            tag.save();
        }
    }
}

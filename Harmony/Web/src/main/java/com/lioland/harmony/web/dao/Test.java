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
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Test {

    public static void main(String[] args) {
//        tags();
        project();
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

    private static void project() {
        Project p = new Project();
        p.setTitle("fssd fsadf");
        p.loadObject();
        Task t = new Task();
        t.setId(UUID.randomUUID().toString());
        t.setName("Do this");
        t.setOwner(new User("chandima@lioland.com"));
        Task tt = new Task();
        tt.setId(UUID.randomUUID().toString());
        tt.setName("Do that");
        t.setOwner(new User("hasanki@lioland.com"));
        Event e = new Event();
        e.setId(UUID.randomUUID().toString());
        e.setName("Event 1");
        e.setTime(new Date());
        e.addTask(tt);
        e.addTask(t);
        p.addEvent(e);
        p.save();
    }
}

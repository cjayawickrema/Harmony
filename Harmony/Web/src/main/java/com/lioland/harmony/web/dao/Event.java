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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Chandima
 */
public class Event extends ODBClass {

    private String id;
    private String name;
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
    private Date time;
    private List<Task> tasks = new ArrayList<>();

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", name=" + name + ", time=" + time + ", tasks=" + tasks + '}';
    }

    public String getShortTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-d 'at' hh:mma");
        return dateFormat.format(time);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String getUniqueFieldName() {
        return "id";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

}

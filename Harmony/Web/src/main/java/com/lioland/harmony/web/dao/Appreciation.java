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
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Chandima
 */
public class Appreciation extends ODBClass {

    private String id;
    private String note;
    private User appreciator;
    private User appreciatee;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date date;

    public String getNote() {
        return note;
    }

    public String getShortDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM, yyyy");
        return sdf.format(date);
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getAppreciator() {
        return appreciator;
    }

    public void setAppreciator(User appreciator) {
        this.appreciator = appreciator;
    }

    public User getAppreciatee() {
        return appreciatee;
    }

    public void setAppreciatee(User appreciatee) {
        this.appreciatee = appreciatee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

}

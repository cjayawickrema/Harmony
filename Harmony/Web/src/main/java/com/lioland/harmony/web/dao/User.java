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
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author Chandima
 */
public class User extends ODBClass {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date joinedDate;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    public List<Project> getProjects() {
        String query = "select * from project where '" + email + "' in users.email";
        return queryList(query, Project.class);
    }

    public List<Appreciation> getReceivedAppreciations() {
        String query = "select * from Appreciation where appreciatee.email = '" + email + "'";
        return queryList(query, Appreciation.class);
    }

    public String getJoinedMonth() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM, yyyy");
        return dateFormat.format(joinedDate);
    }

    public User(String email) {
        this.email = email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getUniqueFieldName() {
        return "email";
    }

    @Override
    public String toString() {
        return "User{" + "firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password=" + password + ", joinedDate=" + joinedDate + ", dateOfBirth=" + dateOfBirth + '}';
    }

}

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

/**
 *
 * @author Chandima
 */
public class Need extends ODBClass {

    private String title;
    private String description;
    private double longtitude;
    private double latitude;
    private String address;
    private String city;
    private String state;
    private String country;
    private User reporter;
    private List<String> images = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getUniqueFieldName() {
        return "title";
    }

//    @Override
//    public String toString() {
//        return "Need{" + "title=" + title + ", description=" + description + ", longtitude=" + longtitude + ", latitude=" + latitude + ", address=" + address + ", city=" + city + ", state=" + state + ", country=" + country + ", reporter=" + reporter + ", tags=" + tags + '}';
//    }
    @Override
    public String toString() {
        return "Need{" + "title=" + title + ", description=" + description + ", longtitude=" + longtitude + ", latitude=" + latitude + ", address=" + address + ", city=" + city + ", state=" + state + ", country=" + country + ", reporter=" + reporter + ", images=" + images + ", tags=" + tags + '}';
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

//    public List<Tag> getTags() {
//        return tags;
//    }
//
//    public void setTags(List<Tag> tags) {
//        this.tags = tags;
//    }
    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}

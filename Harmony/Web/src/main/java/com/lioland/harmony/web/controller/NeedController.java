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
package com.lioland.harmony.web.controller;

import com.lioland.harmony.web.dao.Need;
import com.lioland.harmony.web.dao.User;
import com.lioland.harmony.web.util.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Chandima
 */
@Controller
public class NeedController {

    @RequestMapping(method = RequestMethod.POST, value = "/save-need")
    public String redirectRegister(Need need, HttpServletRequest request) {
        System.out.println(need);
        need.setReporter((User) request.getSession().getAttribute(Constants.SESSION_ATTR_USER));
        need.save();
        System.out.println("Need saved");
        return "report-need";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search-needs")
    @ResponseBody
    public List<Map> searchNeeds(String country, String tags) {
        System.out.println("Seach needs");
        String query = "select * from Need where country='" + country + "' and '" + tags + "' in tags.name";
        List<Need> needs = Need.queryList(query, Need.class);
        List<Map> results = new ArrayList<>();
        for (Need need : needs) {
            Map<String, Object> map = new HashMap<>();
            map.put("title", need.getTitle());
            map.put("country", need.getCountry());
            map.put("city", need.getCity());
            map.put("longtitude", need.getLongtitude());
            map.put("latitude", need.getLatitude());
            map.put("rid", need.getRid());
            results.add(map);
            System.out.println(need);
        }
        return results;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/need-details")
    @ResponseBody
    public Map getNeedDetails(String rid) {
        System.out.println("Get need: " + rid);
        Need need = (Need) Need.querySingle("select * from Need where @rid='" + rid + "'", Need.class);
        System.out.println(need);
        Map<String, Object> map = new HashMap<>();
        map.put("title", need.getTitle());
        map.put("description", need.getDescription());
        map.put("address", need.getAddress());
        map.put("country", need.getCountry());
        map.put("images", need.getImages());
        map.put("city", need.getCity());
        map.put("longtitude", need.getLongtitude());
        map.put("latitude", need.getLatitude());
        map.put("reporter", need.getReporter().getFullName());
        map.put("rid", need.getRid());
        return map;
    }
}

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
import com.lioland.harmony.web.dao.Tag;
import com.lioland.harmony.web.dao.User;
import com.lioland.harmony.web.util.Constants;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Chandima
 */
@Controller
public class NeedController {

    @RequestMapping(method = RequestMethod.POST, value = "/save-need")
    public String redirectRegister(Need need, HttpServletRequest request) {
        need.setReporter((User) request.getSession().getAttribute(Constants.SESSION_ATTR_USER));
        need.save();
        System.out.println("Need saved");
        return "report-need";
    }
}

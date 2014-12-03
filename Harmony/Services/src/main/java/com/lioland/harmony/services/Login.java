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
package com.lioland.harmony.services;

import com.lioland.harmony.services.dao.User;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Chandima
 */
@Path("/login")
public class Login {

    @GET
    @Produces("application/json")
    @Path("/authenticate")
    public User authenticateUser(@QueryParam("email") String email, @QueryParam("password") String password) {
        User user = new User();
        user.setEmail(email);
        user.loadObject();
        if (user.getPassword().equals(password)) {
            user.setPassword(DigestUtils.sha256Hex(user.getEmail().toLowerCase()));
            return user;
        } else {
            return null;
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeJSON(User user) {
        user.setJoinedDate(new Date());
        user.save();
        return Response.ok().build();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lioland.harmony.web.controller;

import com.lioland.harmony.web.dao.Need;
import com.lioland.harmony.web.dao.ODBClass;
import com.lioland.harmony.web.dao.User;
import com.lioland.harmony.web.util.Constants;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Chandima
 */
@Controller
public class DefaultController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String redirect() {
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String redirectHome() {
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/unauthorized")
    public String redirectUnauthorized() {
        return "unauthroized";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/reported-needs")
    public String redirectReportedNeeds() {
        return "reported-needs";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String redirectRegister() {
        return "register";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/report-need")
    public String redirectReportNeed() {
        return "report-need";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "home";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create-project")
    public String redirectCreateProject(String needRid, Model model, HttpServletRequest request) {
        Need need = (Need) Need.querySingle("select * from Need where @rid='" + needRid + "'", Need.class);
        System.out.println(need);

        User user = (User) request.getSession().getAttribute(Constants.SESSION_ATTR_USER);
        List<User> users = ODBClass.queryList("select * from User where @rid<>'" + user.getRid() + "'", User.class);
        System.out.println(users);
        model.addAttribute("need", need);
        model.addAttribute("users", users);
        return "create-project";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/authenticate")
    public String authenticate(String email, String password, HttpServletRequest request) {
        User user = new User();
        user.setEmail(email);
        user.loadObject();
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.SESSION_ATTR_USER);
        if (user.getPassword().equals(password)) {
//            user.setPassword(DigestUtils.sha256Hex(user.getEmail().toLowerCase()));
            session.setAttribute(Constants.SESSION_ATTR_USER, user);
            System.out.println("Authenticated: " + user.getFirstName());
        }
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registerUser")
    public String register(User user, HttpServletRequest request) {
        user.setJoinedDate(new Date());
        user.save();
        System.out.println("User saved: " + user);
        return "home";
    }
}

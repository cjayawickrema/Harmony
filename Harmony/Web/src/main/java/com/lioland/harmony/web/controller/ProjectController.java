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

import com.lioland.harmony.web.dao.Appreciation;
import com.lioland.harmony.web.dao.CashFlow;
import com.lioland.harmony.web.dao.Event;
import com.lioland.harmony.web.dao.ODBClass;
import com.lioland.harmony.web.dao.Project;
import com.lioland.harmony.web.dao.User;
import com.lioland.harmony.web.util.Constants;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Chandima
 */
@Controller
public class ProjectController {

    @RequestMapping(method = RequestMethod.GET, value = "/project-list")
    public String redirectProjectList(Model model, String status) {
        String query;
        if (status == null) {
            query = "select * from Project";
        } else {
            query = "select * from Project where status='" + status + "'";
        }

        List<Project> projects = ODBClass.queryList(query, Project.class);
        model.addAttribute("projects", projects);
        return "project-list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/view-project")
    public String redirectViewProject(Model model, String rid) {
        Project project = (Project) ODBClass.querySingle("select * from Project where @rid='" + rid + "'", Project.class);
        System.out.println(project);
        model.addAttribute("project", project);
        return "view-project";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save-project")
    public String saveProject(Project project, HttpServletRequest request) {
        System.out.println("Saving Project:" + project);
        project.setStatus(Project.INITIATED);
        project.save();
        System.out.println("Project saved");
        return "redirect:home";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save-contribution")
    public String saveContribution(String projectTitle, String name, double amount, HttpServletRequest req) {
        System.out.println("Saving contribution:" + name);
        Project project = new Project();
        project.setTitle(projectTitle);
        project.loadObject();
        CashFlow contribution = new CashFlow();
        contribution.setId(UUID.randomUUID().toString());
        contribution.setAmount(amount);
        contribution.setDate(new Date());
        contribution.setDescription(name);
        contribution.setName(name);
        project.addContribution(contribution);
        project.save();
        System.out.println("Project save: " + project);
        return "redirect:view-project?rid=" + project.getEncodedRid();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save-expense")
    public String saveExpense(String projectTitle, String name, double amount) {
        System.out.println("Saving expense:" + name);
        Project project = new Project();
        project.setTitle(projectTitle);
        project.loadObject();
        CashFlow expense = new CashFlow();
        expense.setId(UUID.randomUUID().toString());
        expense.setAmount(amount);
        expense.setDate(new Date());
        expense.setDescription(name);
        expense.setName(name);
        project.addExpense(expense);
        project.save();
        System.out.println("Project save: " + project);
        return "redirect:view-project?rid=" + project.getEncodedRid();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save-event")
    public String saveEvent(String projectTitle, String name, Date date) {
        System.out.println("Saving Event: " + name);
        Project project = new Project();
        project.setTitle(projectTitle);
        project.loadObject();

        Event e = new Event();
        e.setName(name);
        e.setTime(date);
        e.setId(UUID.randomUUID().toString());
        project.addEvent(e);

        project.save();
        System.out.println("Project save: " + project);
        return "redirect:view-project?rid=" + project.getEncodedRid();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/implement-project")
    public String implementProject(String projectTitle) {
        Project p = new Project();
        p.setTitle(projectTitle);
        p.loadObject();
        p.setStatus(Project.COMMENCED);
        p.save();
        return "redirect:view-project?rid=" + p.getEncodedRid();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accomplish-project")
    public String accomplishProject(String projectTitle) {
        Project p = new Project();
        p.setTitle(projectTitle);
        p.loadObject();
        p.setStatus(Project.ACCOMPLISHED);
        p.save();
        return "redirect:view-project?rid=" + p.getEncodedRid();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/save-appreciation")
    public String saveAppreciation(String projectTitle, String user, String note, HttpServletRequest request) {

        Project p = new Project();
        p.setTitle(projectTitle);
        p.loadObject();

        Appreciation a = new Appreciation();
        a.setAppreciatee(new User(user));
        a.setAppreciator(new User(((User) request.getSession().getAttribute(Constants.SESSION_ATTR_USER)).getEmail()));
        a.setDate(new Date());
        a.setNote(note);
        a.setId(UUID.randomUUID().toString());

        p.addAppreciation(a);
        p.save();

        return "redirect:view-project?rid=" + p.getEncodedRid();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload-project-photos")
    public String uploadProjectPhotos(String projectTitle, String[] files) {
        Project p = new Project();
        p.setTitle(projectTitle);
        p.loadObject();
        for (String file : files) {
            System.out.println("File: " + file);
            p.addImage(file);
        }
        p.save();
        return "redirect:view-project?rid=" + p.getEncodedRid();
    }
}

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

import com.lioland.harmony.web.dao.ODBClass;
import com.lioland.harmony.web.dao.Project;
import java.util.List;
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
    public String redirectProjectList(Model model) {
        List<Project> projects = ODBClass.queryList("select * from Project", Project.class);
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
        project.save();
        System.out.println("Project saved");
        return "home";
    }
}

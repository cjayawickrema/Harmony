<%-- 
    Document   : home
    Created on : Dec 2, 2014, 7:11:51 PM
    Author     : Chandima
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--
Copyright (C) 2014 Chandima

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
-->
<html>
    <head>
        <title>Harmony</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="resources/css/general.css"/>
        <link rel="stylesheet" href="resources/css/home.css"/>
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/bootstrap-growl.min.js"></script>
        <script src="resources/js/general.js"></script>
        <script src="resources/js/home.js"></script>
    </head>
    <body ng-app="home">
        <div class="container">
            <div class="row">
                <div class="col-md-4 phase-list">
                    <ul class="list-group">
                        <li class="list-group-item"><img src="resources/images/explore.svg"/><a href="report-need">Explore and Report Needs</a></li>
                        <li class="list-group-item"><img src="resources/images/project-new.svg"/><a href="reported-needs">Create a Project</a></li>
                        <li class="list-group-item"><img src="resources/images/collaborate.svg"/><a href="project-list">Collaborate with others</a></li>
                        <li class="list-group-item"><img src="resources/images/deploy.svg"/>Implement</li>
                        <li class="list-group-item"><img src="resources/images/accomplish.svg"/>Accomplish</li>
                    </ul>
                </div>
                <div class="col-md-4 text-center">
                    <a href="home"><img width="180" src="resources/images/full-logo.svg" alt="Harmony"/></a>
                </div>
                <div class="col-md-3 col-md-offset-1">
                    <c:choose>
                        <c:when test="${empty sessionScope.user}">
                            <form method="POST" action="authenticate">
                                <div class="form-group">
                                    <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                                </div>
                                <button type="submit" class="btn btn-default">Login</button> or <a href="register">Register Now!</a>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <div>
                                <c:import url="common/user-info.jsp"/>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8"></div>
            </div>
        </div>
    </body>
</html>

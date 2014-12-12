<%-- 
    Document   : view-user
    Created on : Dec 12, 2014, 8:59:11 PM
    Author     : Chandima
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Portfolio</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="resources/css/general.css"/>
        <link rel="stylesheet" href="resources/css/view-user.css"/>
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/view-user.js"></script>
        <script type="text/javascript" src="resources/js/mustache.js"></script>
        <script src="resources/js/general.js"></script>
        <script type="text/javascript">
        </script>
    </head>
    <body>        
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row main-header">
                        <div class="col-md-1 text-center">
                            <a href="home"><img src="resources/images/logo.svg" width="100" /></a>
                        </div>
                        <div class="col-md-9 title"><h2>User Portfolio</h2></div>
                        <c:import url="common/user-info.jsp"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 content-box">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="row">
                                <div class="col-md-2 text-center">
                                    <img src="resources/images/barry.png" width="100"/>
                                </div>
                                <div class="col-md-10">
                                    <h1>${member.fullName}</h1>
                                    <h4 class="grey-text">Member since ${member.joinedMonth}</h4>
                                </div>
                            </div>
                            <hr>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <h3>Projects</h3>
                            <ul class="list-group top-margin">
                                <c:forEach var="project" items="${member.projects}">
                                    <a href="view-project?rid=${project.encodedRid}">
                                        <li class="list-group-item">${project.title}
                                            <span class="badge ${project.status}">${project.status}</span>
                                            <span class="badge">Due on ${project.formattedImplementationDate}</span>
                                        </li>
                                    </a>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="col-md-6">
                            <h3>Appreciations</h3>
                            <div>
                                <c:forEach var="appreciation" items="${member.receivedAppreciations}">
                                    <div class="row top-margin">
                                        <div class="col-md-2 col-xs-2"><img width="60" src="resources/images/appreciate.svg"/></div>
                                        <div class="col-md-9 col-xs-9">
                                            <div class="row">
                                                <div class="col-xs-12">
                                                    <p>${appreciation.note}</p>
                                                </div>
                                                <div class="col-xs-12">
                                                    <p class="grey-text">by <a href="view-user?rid=${appreciation.appreciator.encodedRid}">${appreciation.appreciator.fullName}</a> on ${appreciation.shortDate}</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>  
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

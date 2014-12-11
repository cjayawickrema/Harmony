<%-- 
    Document   : reported-needs
    Created on : Dec 7, 2014, 7:57:48 PM
    Author     : Chandima
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Report a Need</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="resources/css/general.css"/>
        <link rel="stylesheet" href="resources/css/project-list.css"/>
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/project-list.js"></script>
        <script type="text/javascript" src="resources/js/mustache.js"></script>
        <script src="resources/js/general.js"></script>
    </head>
    <body>        
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="row main-header">
                        <div class="col-md-1 text-center">
                            <a href="home"><img src="resources/images/logo.svg" width="100" /></a>
                        </div>
                        <div class="col-md-9 title"><h2>Active Projects</h2></div>
                        <c:import url="common/user-info.jsp"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <ul class="list-group">
                        <c:forEach var="project" items="${projects}">
                            <a href="view-project?rid=${project.encodedRid}"><li class="list-group-item">${project.title}</li></a>
                                </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </body>
</html>
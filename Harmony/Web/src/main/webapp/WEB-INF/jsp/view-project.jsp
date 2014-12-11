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
        <link rel="stylesheet" href="resources/css/view-project.css"/>
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/view-project.js"></script>
        <script type="text/javascript" src="resources/js/mustache.js"></script>
        <script src="resources/js/general.js"></script>
        <script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA4oVKl3sPiEFT1bo0-tGnAy11vUPXJcKg">
        </script>
        <script type="text/javascript">
            var map = null;
            var selectedNeed = null;
            function initialize() {
                var mapOptions = {
                    center: {lat: ${project.need.latitude}, lng: ${project.need.longtitude}},
                    zoom: 12
                };
                map = new google.maps.Map(document.getElementById('need-map'), mapOptions);

                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(${project.need.latitude}, ${project.need.longtitude}),
                    map: map,
                    title: 'Location of Need'
                });

            }
            google.maps.event.addDomListener(window, 'load', initialize);
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
                        <div class="col-md-9 title"><h2>${project.title}</h2></div>
                                <c:import url="common/user-info.jsp"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-5 content-box">
                    <div class="need-info">
                        <h3>${project.need.title}</h3>
                        <p>${project.need.description}</p>
                    </div>
                    <div id="need-map"></div>
                    <div class="row image-container">
                        <c:forEach var="image" items="${project.need.images}">
                            <div class="col-md-6 text-center img-wrapper">
                                <img width="160" src="resources/files/${image}"/>
                            </div>
                        </c:forEach>
                    </div>                    
                </div>
                <div class="col-md-5 content-box">
                    <div class="row">
                        <div class="col-xs-3 text-right">
                            <img src="resources/images/calendar.svg" width="60" />
                        </div>
                        <div class="col-xs-7 pull-left">
                            <div class="form-group">
                                <label for="implementationDate">Implementation Date</label>
                                <p class="implementation-date">${project.formattedImplementationDate}</p>
                            </div>          
                        </div>
                    </div>     
                </div>
                <div class="col-md-5 content-box">
                    <h3>Members</h3>
                    <ul class="list-group">
                        <c:forEach var="user" items="${project.users}">
                            <a href="view-user?rid=${user.encodedRid}"><li class="list-group-item">${user.fullName}</li></a>
                                </c:forEach>
                    </ul>                    
                </div>
                <div class="col-md-5 content-box">
                    <h3>Budget Planning</h3>
                    <div class="row">
                        <div class="col-md-6"></div>
                        <div class="col-md-6"></div>
                    </div>                  
                </div>
                <div class="col-md-5 content-box">
                    <h3>Agenda</h3>
                    <div class="row">
                        <div class="col-md-6"></div>
                        <div class="col-md-6"></div>
                    </div>                  
                </div>                
            </div>
            <div class="row">
                <div class="col-md-3 pull-right">
                    <button class="btn btn-primary">Implement</button>
                </div>
            </div>
        </div>
    </body>
</html>
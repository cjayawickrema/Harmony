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
        <link rel="stylesheet" href="resources/datetimepicker/jquery.datetimepicker.css"/>
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/view-project.js"></script>
        <script type="text/javascript" src="resources/js/mustache.js"></script>
        <script src="resources/js/general.js"></script>
        <script type="text/javascript" src="resources/datetimepicker/jquery.datetimepicker.js"></script>
        <script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA4oVKl3sPiEFT1bo0-tGnAy11vUPXJcKg">
        </script>
        <script type="text/javascript">

            var totalContributions = ${project.totalContributions};
            var totalExpenses = ${project.totalExpenses};

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

            $(document).ready(
                    function () {
                        jQuery('#datetimepicker').datetimepicker();
                    });
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
                    <div class="row top-margin">
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-4"><img width="50" src="resources/images/cash-in.svg"/></div>
                                <div class="col-md-8">
                                    <div>Contributions</div>
                                    <div>${project.totalContributions} LKR</div>
                                </div>
                            </div>
                            <c:forEach var="contribution" items="${project.contributions}">
                                <div class="row top-margin">
                                    <div class="col-md-8">${contribution.name}</div>
                                    <div class="col-md-4">${contribution.amount}</div>
                                </div>
                            </c:forEach>
                            <div class="row top-margin margin-side-5">
                                <form method="post" action="save-contribution">
                                    <input type="hidden" name="projectTitle" value="${project.title}">
                                    <div class="row">
                                        <div class="form-group col-xs-7">
                                            <input type="text" class="form-control" id="contributionName" name="name" placeholder="New...">
                                        </div>
                                        <div class="form-group col-xs-5">
                                            <input type="text" class="form-control" id="contributionAmount" name="amount" placeholder="Amount" >
                                        </div>    
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <button class="btn btn-warning cash-flow-button">Add</button>
                                        </div>
                                    </div>
                                </form>      
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-4"><img width="50" src="resources/images/cash-out.svg"/></div>
                                <div class="col-md-8">
                                    <div>Expenses</div>
                                    <div>${project.totalExpenses} LKR</div>
                                </div>
                            </div>   
                            <c:forEach var="expense" items="${project.expenses}">
                                <div class="row top-margin">
                                    <div class="col-md-8">${expense.name}</div>
                                    <div class="col-md-4">${expense.amount}</div>
                                </div>
                            </c:forEach>
                            <div class="row top-margin margin-side-5">
                                <form method="post" action="save-expense">
                                    <input type="hidden" name="projectTitle" value="${project.title}">
                                    <div class="row">
                                        <div class="form-group col-xs-7">
                                            <input type="text" class="form-control" id="expenseName" name="name" placeholder="New...">
                                        </div>
                                        <div class="form-group col-xs-5">
                                            <input type="text" class="form-control" id="expenseAmount" name="amount" placeholder="Amount" >
                                        </div>                            
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <button class="btn btn-warning cash-flow-button">Add</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-12 text-center"><h2>Budget Difference ${project.totalContributions - project.totalExpenses} LKR</h2></div>
                    </div> 
                </div>
                <div class="col-md-5 content-box">
                    <h3>Agenda</h3>
                    <ul class="list-group">
                        <c:forEach var="event" items="${project.agenda}">
                            <li class="list-group-item">
                                <span class="badge">${event.shortTime}</span>
                                ${event.name}
                            </li>
                        </c:forEach>
                    </ul>
                    <div class="well">
                        <form action="save-event" method="post">
                            <input type="hidden" name="projectTitle" value="${project.title}">
                            <div class="row">
                                <div class="col-md-8">
                                    <input type="text" name="name" class="form-control" placeholder="Name">
                                </div>
                                <div class="col-md-4">
                                    <input id="datetimepicker" type="text" name="date" class="form-control" placeholder="Date">
                                </div>
                            </div>        
                            <div class="row top-margin">
                                <div class="col-md-12">
                                    <button class="btn btn-warning event-date-button">Add Event</button>
                                </div>
                            </div>
                        </form>                        
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
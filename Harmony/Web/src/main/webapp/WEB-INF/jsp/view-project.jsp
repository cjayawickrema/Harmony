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
        <title>Project Details</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="resources/css/general.css"/>
        <link rel="stylesheet" href="resources/css/view-project.css"/>
        <link rel="stylesheet" href="resources/datetimepicker/jquery.datetimepicker.css"/>
        <link rel="stylesheet" href="resources/dropzone/css/dropzone.css"/>
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/view-project.js"></script>
        <script type="text/javascript" src="resources/js/mustache.js"></script>
        <script src="resources/js/general.js"></script>
        <script type="text/javascript" src="resources/datetimepicker/jquery.datetimepicker.js"></script>
        <script type="text/javascript" src="resources/dropzone/dropzone.js"></script>
        <script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA4oVKl3sPiEFT1bo0-tGnAy11vUPXJcKg">
        </script>
        <script type="text/javascript">

            var totalContributions = ${project.totalContributions};
            var totalExpenses = ${project.totalExpenses};
            var projectTitle = '${project.title}';

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
                        <h4>Address</h4>
                        <p>${project.need.address}, ${project.need.city} ${project.need.state} ${project.need.country}</p>
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
                    <div id="member-box">
                        <h3>Members</h3>
                        <ul class="list-group">
                            <c:forEach var="user" items="${project.users}">
                                <a href="view-user?rid=${user.encodedRid}"><li class="list-group-item">${user.fullName}</li></a>
                                    </c:forEach>
                        </ul>
                    </div>                    
                </div>               
                <c:if test="${project.status == 'Commenced' || project.status == 'Accomplished'}">
                    <div class="col-md-10 content-box">
                        <div>
                            <h3>Photos</h3>
                            <div>
                                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                    <!-- Indicators -->
                                    <ol class="carousel-indicators">
                                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                    </ol>

                                    <!-- Wrapper for slides -->
                                    <div class="carousel-inner">
                                        <c:set var="firstTime" value="true"/>
                                        <c:forEach var="image" items="${project.images}">
                                            <c:choose>
                                                <c:when test="${firstTime}">
                                                    <c:set var="firstTime" value="false"/>
                                                    <div class="item active">
                                                        <img class="horizontal-center" src="resources/files/${image}" alt="...">
                                                        <div class="carousel-caption"></div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="item">
                                                        <img class="horizontal-center" src="resources/files/${image}" alt="...">
                                                        <div class="carousel-caption"></div>
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </div>

                                    <!-- Controls -->
                                    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                        <span class="glyphicon glyphicon-chevron-left"></span>
                                    </a>
                                    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                        <span class="glyphicon glyphicon-chevron-right"></span>
                                    </a>
                                </div> <!-- Carousel -->
                            </div>
                            <c:if test="${project.status == 'Commenced'}">
                                <form action="file-upload"
                                      class="dropzone top-margin"
                                      id="my-dropzone"></form>
                                <div class="well">
                                    <button class="btn btn-warning full-width" onclick="uploadPhotos()">Upload</button>
                                </div>
                            </c:if>
                        </div>  
                    </div>
                </c:if>
                <div class="col-md-5 content-box">
                    <h3>Budget Planning</h3>
                    <div class="row top-margin">
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-4"><img width="55" src="resources/images/cash-in.svg"/></div>
                                <div class="col-md-8">
                                    <div>Contributions</div>
                                    <div><h4><strong>${project.totalContributions} ${project.currencyType}</strong></h4></div>
                                </div>
                            </div>
                            <c:forEach var="contribution" items="${project.contributions}">
                                <div class="row top-margin">
                                    <div class="col-md-8">${contribution.name}</div>
                                    <div class="col-md-4">${contribution.amount}</div>
                                </div>
                            </c:forEach>
                            <c:if test="${project.status == 'Commenced' || project.status == 'Initiated'}">
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
                            </c:if>                           
                        </div>
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-4"><img width="60" src="resources/images/cash-out.svg"/></div>
                                <div class="col-md-8">
                                    <div>Expenses</div>
                                    <div><h4><strong>${project.totalExpenses} ${project.currencyType}</strong></h4></div>
                                </div>
                            </div>   
                            <c:forEach var="expense" items="${project.expenses}">
                                <div class="row top-margin">
                                    <div class="col-md-8">${expense.name}</div>
                                    <div class="col-md-4">${expense.amount}</div>
                                </div>
                            </c:forEach>
                            <c:if test="${project.status == 'Commenced' || project.status == 'Initiated'}">
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
                            </c:if>                            
                        </div>
                    </div> 
                    <div class="row">
                        <div class="col-md-12 text-center">
                            <div class="well top-margin">
                                <h4>Budget Difference</h4>
                                <h2>${project.totalContributions - project.totalExpenses} ${project.currencyType}</h2>
                            </div>
                        </div>
                    </div> 
                </div>
                <div class="col-md-5 content-box">
                    <h3>Agenda</h3>
                    <ul class="list-group top-margin">
                        <c:forEach var="event" items="${project.agenda}">
                            <li class="list-group-item">
                                <span class="badge">${event.shortTime}</span>
                                ${event.name}
                            </li>
                        </c:forEach>
                    </ul>
                    <c:if test="${project.status == 'Initiated'}">
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
                    </c:if>                    
                </div>  

                <div class="col-md-5 content-box">
                    <h3>Comments</h3>
                    <c:if test="${project.status == 'Initiated'}">
                        <div>
                            <form action="save-comment" method="post">
                                <input type="hidden" name="projectTitle" value="${project.title}">
                                <div class="row">
                                    <div class="col-md-12">
                                        <input type="text" name="comment" class="form-control" placeholder="Start typing here...">
                                    </div>
                                </div>        
                                <div class="row top-margin">
                                    <div class="col-md-12">
                                        <button class="btn btn-warning comment-date-button">Post</button>
                                    </div>
                                </div>
                            </form>                        
                        </div>
                        <hr/>
                    </c:if>
                    <div>
                        <c:forEach var="comment" items="${project.comments}">
                            <div class="row top-margin">
                                <div class="col-md-2 col-xs-2"><img width="60" src="resources/images/username.svg"/></div>
                                <div class="col-md-9 col-xs-9">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <p>${comment.text}</p>
                                        </div>
                                        <div class="col-xs-12">
                                            <p class="grey-text">by <a href="view-user?rid=${comment.user.encodedRid}">${comment.user.fullName}</a> on ${comment.shortDate}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                        </c:forEach>
                    </div>
                </div>

                <c:if test="${not empty sessionScope.user && project.status == 'Accomplished'}">
                    <div class="col-md-5 content-box">
                        <h3>Share Appreciations</h3>
                        <form action="save-appreciation" method="post">
                            <div class="row top-margin">
                                <div class="col-md-3"><img width="100" src="resources/images/appreciate.svg"/></div>
                                <div class="col-md-9">

                                    <div class="row">
                                        <input type="hidden" name="projectTitle" value="${project.title}">
                                        <div class="form-group col-xs-12">
                                            <select name="user" class="form-control">
                                                <option selected="">Select a Member...</option>
                                                <c:forEach var="user" items="${project.users}">
                                                    <c:if test="${user.email != sessionScope.user.email}">
                                                        <option fullName="${user.fullName}" value="${user.email}">${user.fullName}</option>
                                                    </c:if>                                                
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group col-xs-12">
                                            <textarea class="form-control" name="note" placeholder="Share a note of appreciation for their contribution"></textarea>
                                        </div>
                                    </div>

                                </div>
                            </div>  
                            <div class="row">
                                <div class="col-md-12">
                                    <button class="btn btn-warning full-width" type="submit">Publish</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="row">
                <div class="col-md-11">
                    <c:choose>
                        <c:when test="${project.status == 'Initiated'}">
                            <button class="btn btn-primary full-width" onclick="implementProject()">Commence Project</button>
                        </c:when>
                        <c:when test="${project.status == 'Commenced'}">
                            <button class="btn btn-success full-width" onclick="accomplishProject()">Mark Project as Accomplished</button>
                        </c:when>
                        <c:when test="${project.status == 'Accomplished'}">
                            <h2 class="full-width text-center"><span class="label label-success full-width">This Project has been Accomplished</span></h2>
                        </c:when>
                    </c:choose>                    
                </div>
            </div>
        </div>
    </body>
</html>
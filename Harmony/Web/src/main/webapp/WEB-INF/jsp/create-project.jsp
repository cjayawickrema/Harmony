<%-- 
    Document   : create-project
    Created on : Dec 11, 2014, 10:07:04 AM
    Author     : Chandima
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${empty sessionScope.user}">
    <c:redirect url="/unauthorized"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <title>Create Project</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="resources/css/general.css"/>   
        <link rel="stylesheet" href="resources/css/create-project.css"/>      
        <link href="resources/datepicker/css/datepicker.css" rel="stylesheet"/>        
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/datepicker/js/bootstrap-datepicker.js"></script>
        <script src="resources/js/general.js"></script>
        <script type="text/javascript" src="resources/js/create-project.js"></script>
        <script type="text/javascript">
            var needTitle = '${need.title}';

            $(document).ready(
                    function () {
                        $('.datefield').datepicker({format: 'dd/mm/yyyy'});
                    }
            );
        </script>
    </head>
    <body>  
        <form method="post" action="save-project">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="row main-header">
                            <div class="col-md-1 text-center">
                                <a href="home"><img src="resources/images/logo.svg" width="100" /></a>
                            </div>
                            <div class="col-md-9 title text-center"><input type="text" name="title" id="title" placeholder="Provide a name for this Project..." /></div>
                                <c:import url="common/user-info.jsp"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5 content-box">
                        <h3>Project Settings</h3>
                        <p class="top-margin">Allow others to join this project by</p>
                        <div class="form-group">
                            <label><input type="checkbox" id="donorsAllowed" name="donorsAllowed" checked=""/>Donating</label>
                            <label><input type="checkbox" id="participantsAllowed" name="participantsAllowed" checked=""/>Participating</label>
                            <label><input type="checkbox" id="coordinatorsAllowed" name="coordinatorsAllowed" checked=""/>Coordinating</label>
                        </div>
                        <hr/>
                        <div class="form-group">
                            <label for="currencyType">Currency Type</label>
                            <input type="text" class="form-control" id="currencyType" name="currencyType" placeholder="e.g. LKR, USD, GBP"/>
                        </div>
                        <hr/>
                        <div class="form-group">
                            <label for="accountName">Account Owner Name</label>
                            <textarea type="text" class="form-control" id="accountName" name="accountName"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="accountNumber">Account Number</label>
                            <input type="text" class="form-control" id="accountNumber" name="accountNumber">
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="bank">Bank</label>
                                <input type="text" class="form-control" id="bank" name="bank">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="branch">Branch</label>
                                <input type="text" class="form-control" id="branch" name="branch">
                            </div>                            
                        </div>           
                    </div>
                    <div class="col-md-5 content-box">
                        <div class="need-info">
                            <h3>${need.title}</h3>
                            <p>${need.description}</p>
                        </div>
                        <div class="row image-container">
                            <c:forEach var="image" items="${need.images}">
                                <div class="col-md-6 text-center img-wrapper">
                                    <img width="160" src="resources/files/${image}"/>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-5 content-box">
                        <div class="form-group">
                            <label for="description">Project Description</label>
                            <textarea type="text" class="form-control" id="description" name="description"></textarea>
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
                                    <input class="form-control datefield" name="implementationDate" id="implementationDate" />
                                </div>          
                            </div>
                        </div>     
                    </div>
                    
                    <div class="col-md-5 content-box">
                        <div class="need-info">
                            <h3>Project Members</h3>
                            <p>Add <b>Groups</b> or <b>Individuals</b></p>
                            <div class="row">
                                <div class="form-group col-xs-9">
                                    <select id="user" class="form-control">
                                        <c:forEach var="user" items="${users}">
                                            <option fullName="${user.fullName}" value="${user.email}">${user.fullName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group col-xs-2">
                                    <button onclick="addUser()" class="btn btn-warning" type="button">Add</button>
                                </div>                            
                            </div>
                            <hr/>
                            <ul class="list-group" id="users"></ul>
                            <select multiple="" id="user-list" name="users">
                                <option selected="" value="${sessionScope.user.email}"></option>
                            </select>
                            <input type="hidden" name="need.title" value="${need.title}" />
                        </div>
                    </div>                    
                </div>
                <div class="row">
                    <div class="col-md-2 col-md-offset-9">
                        <button type="submit" class="btn btn-primary">Save Project</button>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>

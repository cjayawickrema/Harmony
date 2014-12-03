<%-- 
    Document   : header
    Created on : Dec 2, 2014, 9:56:26 PM
    Author     : Chandima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="row">
    <div class="col-md-12">
        <div class="row main-header">
            <div class="col-md-1 text-center">
                <a href="home"><img src="resources/images/logo.svg" width="100" /></a>
            </div>
            <div class="col-md-9 title text-center"><input type="text" name="title" form="need-form" /></div>
            <div class="col-md-2 user-name text-center">${sessionScope.user.firstName} ${sessionScope.user.lastName}</div>
        </div>
    </div>
</div>
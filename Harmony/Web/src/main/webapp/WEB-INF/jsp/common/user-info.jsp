<%-- 
    Document   : user-info
    Created on : Dec 11, 2014, 12:38:16 PM
    Author     : Chandima
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${not empty sessionScope.user}">
    <a href="view-user?rid=${sessionScope.user.encodedRid}"><div class="user-name text-right">${sessionScope.user.fullName}</div></a>    
    <button class="btn btn-warning pull-right logout-button" onclick="logout()">Logout</button>
</c:if>

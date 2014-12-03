<%-- 
    Document   : report-need
    Created on : Dec 2, 2014, 9:32:22 PM
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
        <link rel="stylesheet" href="resources/css/register.css"/>
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/bootstrap-growl.min.js"></script>
        <script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA4oVKl3sPiEFT1bo0-tGnAy11vUPXJcKg">
        </script>
        <script type="text/javascript">
            var needLocation;
            var marker;

            function initialize() {
                var mapOptions = {
                    center: {lat: 8.29252501135247, lng: 80.5182409286499},
                    zoom: 12
                };
                var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
                google.maps.event.addListener(map, 'click', function (args) {
                    needLocation = args.latLng;
                    if(marker) {
                        marker.setMap(null);
                    }
                    marker = new google.maps.Marker({
                        position: needLocation,
                        map: map,
                        title: "This is the location"
                    });
                    $('#longtitude').val(needLocation.lng());
                    $('#latitude').val(needLocation.lat());
                });
            }
            google.maps.event.addDomListener(window, 'load', initialize);


        </script>
    </head>
    <body>        
        <div class="container">
            <c:import url="common/header.jsp"/>
            <div class="row">
                <div class="col-md-5">
                    <form method="post" action="save-need" id="need-form">
                        <div class="form-group">
                            <label for="description">Description</label>
                            <textarea type="text" class="form-control" id="description" name="description"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" id="address" name="address">
                        </div>
                        <div class="row">
                            <div class="form-group col-xs-6">
                                <label for="city">City</label>
                                <input type="text" class="form-control" id="city" name="city">
                            </div>
                            <div class="form-group col-xs-6">
                                <label for="state">State</label>
                                <input type="text" class="form-control" id="state" name="state">
                            </div>                            
                        </div>
                        <div class="form-group">
                            <label for="country">Country</label>
                            <input type="text" class="form-control" id="country" name="country">
                        </div>
                        <div class="form-group">
                            <label for="tags">Tags</label>&nbsp;(Ctrl + Click)
                            <select class="form-control" id="tags" name="tags" multiple size="9">
                                <option value="Educational">Educational</option>
                                <option value="Food">Food</option>
                                <option value="Water">Water</option>
                                <option value="Construction">Construction</option>
                                <option value="Clothes">Clothes</option>
                                <option value="Medication">Medication</option>
                                <option value="Organs">Organs</option>
                                <option value="Blood">Blood</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>               
                        <input type="hidden" name="longtitude" id="longtitude" />
                        <input type="hidden" name="latitude" id="latitude" />
                        <button class="btn btn-primary" type="submit">Publish</button>
                        <button class="btn btn-default" type="reset">Reset</button>
                    </form>                    
                </div>
                <div class="col-md-7">
                    <div id="map-canvas"></div>
                </div>
            </div>
        </div>
    </body>
</html>

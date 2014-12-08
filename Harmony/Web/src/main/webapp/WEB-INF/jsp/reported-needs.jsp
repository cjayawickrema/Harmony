<%-- 
    Document   : reported-needs
    Created on : Dec 7, 2014, 7:57:48 PM
    Author     : Chandima
--%>

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
        <link rel="stylesheet" href="resources/css/reported-needs.css"/>
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/bootstrap-growl.min.js"></script>
        <script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA4oVKl3sPiEFT1bo0-tGnAy11vUPXJcKg">
        </script>
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
                        <div class="col-md-9 title"><h2>Reported Needs</h2></div>
                        <div class="col-md-2 user-name text-center">${sessionScope.user.firstName} ${sessionScope.user.lastName}</div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="row need-search-filters">
                        <div class="col-md-3 search-filter-wrapper">
                            <div class="form-group">
                                <label for="tags">Tags</label>
                                <select class="form-control" id="tags" name="tags">
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
                        </div>
                        <div class="col-md-3 search-filter-wrapper">
                            <div class="form-group">
                                <label for="reporter">Reported by</label>
                                <select class="form-control" id="reporter" name="reporter">
                                    <option value="Educational">-select-</option>
                                </select>
                            </div>
                        </div>                        
                        <div class="col-md-3 search-filter-wrapper">
                            <div class="form-group">                             
                                <label class="radio-inline">
                                    <input type="radio" name="optradio">Map View
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="optradio">Option 2
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="optradio">Option 3
                                </label>
                            </div>
                        </div>
                        <div class="col-md-3"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
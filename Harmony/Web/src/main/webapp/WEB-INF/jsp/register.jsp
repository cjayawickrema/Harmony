<%-- 
    Document   : register
    Created on : Dec 2, 2014, 8:42:37 PM
    Author     : Chandima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Register</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/css/bootstrap.min.css">
        <link rel="stylesheet" href="resources/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="resources/css/general.css"/>
        <link rel="stylesheet" href="resources/css/register.css"/>
        <script src="resources/js/jquery-1.11.1.min.js"></script>
        <script src="resources/js/bootstrap.min.js"></script>
        <script src="resources/js/register.js"></script>
        <script src="resources/js/general.js"></script>
        <script type="text/javascript" src="resources/js/bootstrap-growl.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row text-center">
                <div class="col-md-4 col-md-offset-4">
                    <img src="resources/images/full-logo.svg" width="180" />
                    <form method="post" action="registerUser">
                        <div class="form-group">
                            <input type="text" class="form-control" name="firstName" placeholder="First Name">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="lastName" placeholder="Last Name">
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" name="email" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="password" placeholder="Password">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm Password">
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox">I agree to the Terms and Conditions
                            </label>
                        </div>
                        <button>Register</button> or <a href="home">Login</a>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>

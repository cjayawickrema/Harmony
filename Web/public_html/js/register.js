/* 
 * Copyright (C) 2014 Chandima
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

angular.module("registration", [])
        .controller("RegistrationCtrl", function ($scope, $http) {
            $scope.myData = {};
            $scope.register = function (user) {

                var responsePromise = $http.post("http://localhost:81/services/login/register", user);

                responsePromise.success(function (data, status, headers, config) {
                    alert(data);
                });
                responsePromise.error(function (data, status, headers, config) {
                    alert("AJAX failed!");
                });
            }


        });

//angular.module('registration', [])
//        .controller('RegistrationCtrl', ['$scope', function ($scope, $http) {
//
//                $scope.register = function (user) {
//                    alert($http + '1');
//                    $http.post('http://localhost:8080/Services/rest/login/register', user)
//                            .success(function (data, status, headers, config) {
//                                alert(data);
//                            }).error(function (data, status, headers, config) {
//                        alert('Error');
//                    });
//                };
//            }]);
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
angular.module("home", [])
        .controller("AuthenticationCtrl", function ($scope, $http) {
            $scope.myData = {};
            $scope.authenticate = function (credentials) {
                var responsePromise = $http.get("http://localhost:81/services/login/authenticate", {params: credentials});

                responsePromise.success(function (data, status, headers, config) {
                    if (data) {
                        alert('Hello ' + data.firstName);
                    } else {
                        alert('invalid');
                    }

                });
                responsePromise.error(function (data, status, headers, config) {
                    alert("Error!");
                });
            }


        });
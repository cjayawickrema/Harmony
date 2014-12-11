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

var users = [];

/**
 * add users to project
 */
function addUser() {
    var fullName = $('#user option:selected').attr('fullName');
    var email = $('#user').val();

    if (!email) {
        return;
    }

    users.push({email: email});

    $('#users').append($('<li>').html($('<img>').attr('src', 'resources/images/individual.svg').attr('width', '16')).append(fullName).addClass('list-group-item'));
    $('#user-list').append($('#user option:selected').remove());
}

/**
 * Comment
 */
function saveProject() {
//    var project = {};
//
//    project.title = $('#title').val();
////    project.users = users;
//    project.need = needTitle;
//    project.implementationDate = $('#implementationDate').val();
//    project.description = $('#description').val();
//    project.donorsAllowed = $('#donorsAllowed').is(':checked');
//    project.participantsAllowed = $('#participantsAllowed').is(':checked');
//    project.coordinatorsAllowed = $('#coordinatorsAllowed').is(':checked');
//    project.accountName = $('#accountName').val();
//    project.accountNumber = $('#accountNumber').val();
//    project.bank = $('#bank').val();
//    project.branch = $('#branch').val();
//
//    post('save-project', project);
}
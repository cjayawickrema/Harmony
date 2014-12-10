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

/**
 * Search Needs.
 */
function searchNeeds() {
    var country = $('#country').val();
    var tags = $('#tags').val();

    $.ajax({
        dataType: "json",
        url: 'search-needs',
        data: {country: country, tags: tags},
        success: function (json) {
            needList = json;
            renderNeeds();
        }
    });
}

/**
 * Comment
 */
function renderNeeds() {
    var viewMode = $('input[name=viewMode]:checked').val();
    if (viewMode === 'map') {
        renderNeedMap();
    } else {
        renderNeedList();
    }

}

/**
 * Render need list
 */
function renderNeedList() {
    alert('list');
}

/**
 * Comment
 */
function renderNeedMap() {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
    }
    markers = [];
    if (!needList || needList.length === 0) {
        return;
    }
    for (var i = 0; i < needList.length; i++) {
        var need = needList[i];

        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(need.latitude, need.longtitude),
            map: map,
            title: need.title
        });
        marker.need = need;
        markers.push(marker);

        google.maps.event.addListener(marker, 'click', function () {
            getNeedDetails(this.need);
        });
    }

    var bounds = new google.maps.LatLngBounds();
    for (i = 0; i < markers.length; i++) {
        bounds.extend(markers[i].getPosition());
    }

    map.fitBounds(bounds);
}

/**
 * getNeedDetails
 */
function getNeedDetails(need) {
    $.ajax({
        dataType: "json",
        url: 'need-details',
        data: {rid: need.rid},
        success: function (need) {
            $.get('resources/templates/need-details.mst', function (template) {
                var rendered = Mustache.render(template, need);
                $('#need-detail').html(rendered);
            });
            
             $.get('resources/templates/need-images.mst', function (template) {
                var rendered = Mustache.render(template, need.images);
                $('#need-images').html(rendered);
            });
        }
    });
}
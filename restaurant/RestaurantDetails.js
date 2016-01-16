

(function(){
    var module = angular.module("restaurantReservations")

    var RestaurantDetails = function($scope, $http) {
      var _selected;
  	  var cities = "Atlanta, USA";
	  var geocoder= new google.maps.Geocoder();
	  
	   $scope.markers = [];
	   
	   var createMarker = function (info){
	        var marker = new google.maps.Marker({
	            map: $scope.map,
	            position: new google.maps.LatLng(info.lat(), info.lng())
	        });
	   }

	   geocoder.geocode( { 'address': cities }, function(results, status) {
	    if (status == google.maps.GeocoderStatus.OK) {
	        newAddress = results[0].geometry.location;
	        $scope.map.setCenter(newAddress);
	        createMarker(newAddress)
	    }
	   });

	  $scope.mapOptions = {
	        zoom: 12,
	        //center: new google.maps.LatLng(41.923, 12.513),
	        mapTypeId: google.maps.MapTypeId.TERRAIN
	    }

	    $scope.map = new google.maps.Map(document.getElementById('map'), $scope.mapOptions);
	    
	    
	    //issue of google maps inside bootstap model issue
	    $('#myModal').on('shown.bs.modal', function(){
	    google.maps.event.trigger(map, 'resize');
	   $scope.map.setCenter(new google.maps.LatLng(newAddress.lat(), newAddress.lng()));
	  });
	    

	   
	   $( "#accordion" ).accordion();	    
	    
    }

    module.controller("RestaurantDetails", RestaurantDetails)

}())


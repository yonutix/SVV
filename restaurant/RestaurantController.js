

(function(){
    var module = angular.module("restaurantReservations")

    var RestaurantController = function($scope, $http,$location) {
      var _selected;
  	  var cities = "Atlanta, USA";
	  var geocoder= new google.maps.Geocoder();
	  $('#nav').hide();
	  if (!$("#checkbox_edit")[0].checked)
      		$("#btn").hide()
	   $scope.markers = [];
	   var restId=$location.search().restId;
	   var email = $location.search().email;
	   console.log(restId);
	   var restaurant = {"restaurant_id":restId}
		console.log(restaurant)
	   $http.post('http://localhost:3000/getrestaurant', restaurant).then(
			   function successCallback(response) {
				 $scope.details = JSON.parse(angular.toJson(response))
				 $scope.details = $scope.details.data
				console.log($scope.details)
				var coord = $scope.details.address.coord
				geocoder.geocode( { 'address': cities }, function(results, status) {
                	    if (status == google.maps.GeocoderStatus.OK) {
                	        newAddress = new google.maps.LatLng(coord[1], coord[0]);
                	        $scope.map.setCenter(newAddress);
                	        createMarker(newAddress)
                	    }
                	   });
				}, function errorCallback(response) {
				  alert("error")
		});

	$scope.save = function(){
		$scope.details.email = email
		$scope.details.restaurant_id = restId
		$scope.details.all_spots = $scope.details.free_spots
		console.log($scope.details)
		$http.post('http://localhost:3000/newrestaurant', $scope.details).then(
			 function successCallback(response) {
				  resp = JSON.parse(angular.toJson(response.data))
				  console.log(resp)
				}, function errorCallback(response) {
					alert("error")
			 	});

	}


	$("#checkbox_edit").change(function(){
		if ($("#checkbox_edit")[0].checked)
			$("#btn").show()
		else $("#btn").hide()
		})


	   var createMarker = function (info){
	        var marker = new google.maps.Marker({
	            map: $scope.map,
	            position: new google.maps.LatLng(info.lat(), info.lng())
	        });
	   }



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

    module.controller("RestaurantController", RestaurantController)

}())


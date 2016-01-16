(function(){
    var module = angular.module("restaurantReservations")

    var MainController = function($scope, $http) {
        var _selected;

          $scope.selected = undefined;

           $scope.go = function() {
//           $( "#accordion" ).hide();
//           alert($scope.search.date);
        $http.get('http://192.168.0.172:3000/restaurants').then(
            function successCallback(response) {
             alert("e ok")
           }, function errorCallback(response) {
             alert("error")
           });
//    $http.get("http://192.168.0.172:3000/restaurants").then(function(response) {
//            alert("e ok")
//        });
            }
         var restaurants = ['A', 'B', 'C', 'D', 'E', 'F'];
         var kitchens=['k1','k2','k3','k4'];
//         $http.get("http://192.168.0.172:3000/restaurants")
         $http({
           method: 'GET',
           url: 'http://192.168.0.172:3000/restaurants'
         }).then(function successCallback(response) {
             alert(response)
           }, function errorCallback(response) {
             // called asynchronously if an error occurs
             // or server returns response with an error status.
           });

 $( "#input5" ).autocomplete({
      source: restaurants
    });
 $( "#input6" ).autocomplete({
      source: kitchens
    });
 $( "#accordion" ).accordion();
    };
    module.controller("MainController", MainController)

}())


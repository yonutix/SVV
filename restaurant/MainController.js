(function(){
    var module = angular.module("restaurantReservations")

    var MainController = function($scope, $http) {
        var _selected;

          $scope.selected = undefined;

           $scope.go = function() {
           $( "#accordion" ).hide();
           alert($scope.search.date);
            }
         var restaurants = ['A', 'B', 'C', 'D', 'E', 'F'];
         var kitchens=['k1','k2','k3','k4'];

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


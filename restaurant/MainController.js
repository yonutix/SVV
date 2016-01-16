(function(){
    var module = angular.module("restaurantReservations")

    var MainController = function($scope, $http) {
        var _selected;
//        $( "#accordion" ).hide();
        $scope.selected = undefined;
        $scope.search = {}
        $scope.search.city = "Bucharest"
        $scope.search.numSpots = 2
        var d = new Date();
        var year=d.getFullYear();
        var month=d.getMonth()+1;
        $scope.restaurants = undefined;
        if (month<10){
            month= "0" + month;
        };
        var day=d.getDate();
//        $scope.search.hour = d.getHours() + ":" + d.getMinutes()
        $scope.search.date = year + "-" + month + "-" + day;


        $scope.go = function() {

            $http.post('http://192.168.0.172:3000/restaurants', $scope.search).then(
                function successCallback(response) {
//                var restaurants = JSON.parse(angular.toJson(response))
//                restaurants = angular.toJson(restaurants.data)
                $scope.restaurants = JSON.parse(angular.toJson(response))
                $scope.restaurants = $scope.restaurants.data
//                alert(angular.toJson($scope.restaurants))
                $scope.items = [
                   {Name: "Soap",  Price: "25",  Quantity: "10"},
                   {Name: "Bag",   Price: "100", Quantity: "15"},
                   {Name: "Pen",   Price: "15",  Quantity: "13"}
               ];
               }, function errorCallback(response) {
                 alert("error")
               });
//            $( "#accordion" ).show();
        }
        var restaurants_names = ['A', 'B', 'C', 'D', 'E', 'F'];
        var kitchens=['k1','k2','k3','k4'];


        $( "#input5" ).autocomplete({
              source: restaurants_names
        });
        $( "#input6" ).autocomplete({
              source: kitchens
        });
        $( "#accordion" ).accordion();
     };
     module.controller("MainController", MainController)

}())


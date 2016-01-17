(function(){
    var module = angular.module("restaurantReservations")

    var MainController = function($scope, $http, $location, $rootScope) {
       Number.prototype.pad = function (len) {
           return (new Array(len+1).join("0") + this).slice(-len);
       }
        var _selected;

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
       $scope.search.hour = (d.getHours()+1).pad(2) + ":"
                                        + d.getMinutes().pad(2) + ":00"

        $scope.search.date = year + "-" + month + "-" + day;
        var prices = ["Cheap", "Affordable", "Expensive"]

        $scope.go = function() {
        var name=$( "#input5" ).val();
        var cuisine=$( "#input6" ).val();
        if (name!="")
            $scope.search.name=name;
        if (cuisine!="")
            $scope.search.cuisine=cuisine;

            $http.post('http://localhost:3000/restaurants', $scope.search).then(
                function successCallback(response) {
                $scope.restaurants = JSON.parse(angular.toJson(response))
                $scope.restaurants = $scope.restaurants.data

               }, function errorCallback(response) {
                 alert("error")
               });
        };


        var kitchens=['American ','Irish','Delicatessen','Hamburgers', 'Ice Cream, Gelato, Yogurt, Ices',
                        'Chinese', 'Bakery', 'Turkish', 'Caribbean', 'Chicken', 'Donuts', 'Bagels/Pretzels',
                        'Continental', 'Pizza', 'Steak', 'Italian', 'German', 'Sandwiches/Salads/Mixed Buffet'];

        $http.get('http://localhost:3000/restlist').then(
            function successCallback(response) {
            $scope.restaurants_names = JSON.parse(angular.toJson(response)).data.restlist
            $( "#input5" ).autocomplete({
                  source: $scope.restaurants_names
            })
           }, function errorCallback(response) {
             alert("error")
        });


        $( "#input6" ).autocomplete({
              source: kitchens
        });

        $scope.book = function(item) {
            $rootScope.search = {}
            $rootScope.search.name = item.name;
            $rootScope.search.cuisine = item.cuisine;
            $rootScope.search.city = $scope.search.city;
            $rootScope.search.date = $scope.search.date;
            $rootScope.search.hour = $scope.search.hour;
            $rootScope.search.numSpots = $scope.search.numSpots;
            $location.path('/reservation/');
        };

    };
    module.controller("MainController", MainController)

}())
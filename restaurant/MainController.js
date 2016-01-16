(function(){
    var module = angular.module("restaurantReservations")
//    module.config(['$controllerProvider', function($controllerProvider) {
//        $controllerProvider.allowGlobals();
//    }]);

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
        var prices = ["Cheap", "Affordable", "Expensive"]
        $scope.go = function() {

            $http.post('http://192.168.0.172:3000/restaurants', $scope.search).then(
                function successCallback(response) {
//                var restaurants = JSON.parse(angular.toJson(response))
//                restaurants = angular.toJson(restaurants.data)
                $scope.restaurants = JSON.parse(angular.toJson(response))
                $scope.restaurants = $scope.restaurants.data
//                alert(angular.toJson($scope.restaurants))
                $scope.range = prices[$scope.restaurants.priceRange]
               }, function errorCallback(response) {
                 alert("error")
               });
//            $( "#accordion" ).show();
        }
        var kitchens=['American ','Irish','Delicatessen','Hamburgers', 'Ice Cream, Gelato, Yogurt, Ices',
                        'Chinese', 'Bakery', 'Turkish', 'Caribbean', 'Chicken', 'Donuts', 'Bagels/Pretzels',
                        'Continental', 'Pizza', 'Steak', 'Italian', 'German', 'Sandwiches/Salads/Mixed Buffet'];

        $http.get('http://192.168.0.172:3000/restlist').then(
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
//              select: function (event, ui) {
////                          var v = ui.item.value;
////                          $('#input6').val(ui.item.value);
//                      }
        });
        $( "#accordion" ).accordion();



    };
    module.controller("MainController", MainController)

}())


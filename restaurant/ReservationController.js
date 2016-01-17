(function(){
    var module = angular.module("restaurantReservations")

    var ReservationController = function($scope, $rootScope, $http) {
//        $scope.search = {}
        $('#loginData').hide();
        $('#successful_res').hide();
        $('#failure_res').hide();
        $scope.search.name = $rootScope.search.name
        $scope.search.cuisine = $rootScope.search.cuisine
        $scope.search.city = $rootScope.search.city
        $scope.search.date = $rootScope.search.date
        $scope.search.hour = $rootScope.search.hour
        $scope.search.numSpots = $rootScope.search.numSpots
        $scope.user = {}
        if ($rootScope.userData!=undefined) {
            $scope.user.name= $rootScope.userData.name;
            $scope.user.phone= $rootScope.userData.phone;
            $scope.user.email= $rootScope.userData.email;
        }

        $scope.bookNow = function() {
            console.log($scope.search)
            $http.post('http://localhost:3000/book', $scope.search).then(
                function successCallback(response) {
                    message = JSON.parse(angular.toJson(response))
                    message = message.data.response
                    console.log(angular.toJson(message))
                    $('#reservation').hide();
                    if (message=="success")
                        $('#successful_res').show();
                    else $('#failure_res').show();
               }, function errorCallback(response) {
                 alert("error")
               });
        };

    };
    module.controller("ReservationController", ReservationController)

}())
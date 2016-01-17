(function(){
    var module = angular.module("restaurantReservations")

    var RegisterController = function($scope, $http) {
        $scope.user = {}
        $scope.user.type="client"
        $('#loginData').hide();
        $('#successful_res').hide();
        $('#failure_res').hide();
        $scope.register = function() {
            console.log($scope.user)
            $http.post('http://localhost:3000/newuser', $scope.user).then(
                function successCallback(response) {
                    message = JSON.parse(angular.toJson(response))
                    message = message.data.response
                    console.log(angular.toJson(message))
                    $('#register').hide();
                    if (message=="success")
                        $('#successful_res').show();
                    else $('#failure_res').show();
               }, function errorCallback(response) {
                 alert("error")
               });
        };

    };
    module.controller("RegisterController", RegisterController)

}())
(function(){
    var module = angular.module("restaurantReservations")

    var LoginController = function($scope, $http, $location, $rootScope) {
    $('#welcomeLogin').hide();
    $scope.signIn=function(){
    $http.post('http://localhost:3000/login', $scope.login).then(
                   function successCallback(response) {
                   $scope.userData = JSON.parse(angular.toJson(response))
                   $scope.userData = $scope.userData.data
                    if( $scope.userData.response=='fail')
                     $location.path('/register/');
                    else{
                    $rootScope.userData=$scope.userData;
                    console.log($rootScope.userData);
                        $('#loginData').hide();
                        $('#welcomeLogin').show();
                    if ($scope.userData.type=="manager")
                        $location.path('/details').search('restId', $scope.userData.id_restaurant)
                        .search('email', $scope.userData.email);
                    }
                  }, function errorCallback(response) {
                    alert("error")
                  });
    }

    $scope.logout=function(){
        $('#loginData').show();
        $('#welcomeLogin').hide();
        $rootScope.userData = undefined
    }





    };
    module.controller("LoginController", LoginController)

}())
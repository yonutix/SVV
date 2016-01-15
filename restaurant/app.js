(function(){

  var app = angular.module("restaurantReservations", ["ngRoute", 'ngAnimate', 'ui.bootstrap'])

  app.config(function($routeProvider){
    $routeProvider
      .when("/main", {
              templateUrl: "main.html",
              controller: "MainController"
            })
      .when("/register", {
              templateUrl: "register.html",
              controller: "RegisterController"
            })
      .otherwise({redirectTo:"/main"})
  })
}())
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
      .when("/details", {
              templateUrl: "details.html",
              controller: "RestaurantDetails"
            })
      .otherwise({redirectTo:"/main"})
  })
}())


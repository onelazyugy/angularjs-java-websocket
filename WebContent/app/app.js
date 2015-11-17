var app = angular.module('toolrentalsimulator', [
    'toolrentalsimulator.controllers',
    'toolrentalsimulator.services',
    'toolrentalsimulator.directives',
    'toolrentalsimulator.filters',
    'ui.router',
    'ui.bootstrap',
    'hljs',
    'ui.codemirror',
    'ngWebsocket'
]).config(function($urlRouterProvider, $stateProvider) {
    'use strict';  
    $urlRouterProvider.when("/", "home");
    $urlRouterProvider.when("/test", "/test");
    $urlRouterProvider.otherwise("/");
    
    $stateProvider
    .state("home", {
        url: "/home",
        templateUrl: "app/view/index.html",
        controller: "indexCtrl"
    })/*.state("test", {
        url: "/test",
        templateUrl: "app/view/index.html",
        controller: "indexCtrl"
    })*/;
});

var controllers = angular.module('toolrentalsimulator.controllers', []);
var services = angular.module('toolrentalsimulator.services', []);
var directives = angular.module('toolrentalsimulator.directives', []);
var filters = angular.module('toolrentalsimulator.filters', []);

controllers.controller('MainCtrl', ['$scope', '$state', '$rootScope',
    function($scope, $state, $rootScope) {
        $scope.test = function(){
            alert();
        };
    }
]);
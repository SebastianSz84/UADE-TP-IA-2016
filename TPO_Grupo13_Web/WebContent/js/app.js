/**
 * Created by gusta on 23/11/2015.
 */
angular.module('integracion', [
        'ui.router'
    ])
    .config(function ($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('login', {
                url: '/login',
                templateUrl: 'js/login/login.html',
                controller: 'LoginCtrl'
            })
            .state('home', {
                url: '/home',
                templateUrl: 'js/home/home.html',
                controller: 'HomeCtrl'
            });
        $urlRouterProvider.otherwise('/login');
    });
/**
 * Created by gusta on 
 */
angular.module('integracion')
    .controller('HomeCtrl', function($scope, $http, $rootScope, $timeout) {
    	
    	$scope.title = "Son todos giles!";
    	$scope.products = [];
    	
    	$http({
            'method':'get',
            'url':'http://localhost:8080/TPO_Grupo13_Web/ListadoProductos'
		})
        .success(function (data) {
        	$scope.products = data;
        }).
        error(function (data, status) {
            console.log(data);
            console.log(status);
        });

    	  $scope.sortBy = function(propertyName) {
    		    $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
    		    $scope.propertyName = propertyName;
    		  };
    });
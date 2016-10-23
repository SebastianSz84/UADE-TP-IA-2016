/**
 * Created by gusta on 
 */
angular.module('integracion')
    .controller('HomeCtrl', function($scope, $http, $rootScope, $timeout) {
    	
    	$scope.user = $rootScope.user;
    	if ($scope.user)
    		$scope.title = "Bienvenido, " +$scope.user.nombre+"!";
    	else
    		$scope.title = "Bienvenido!"
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
    	$scope.add = function(){
    		alert("crear carrito");
    	}
    	
    	$scope.getBestSellers = function(){
        	$http({
                'method':'get',
                'url':'http://localhost:8080/TPO_Grupo13_Web/ListadoBestSellers'
    		})
            .success(function (data) {
            	$scope.products = data;
            }).
            error(function (data, status) {
                console.log(data);
                console.log(status);
            });

    	}
    });
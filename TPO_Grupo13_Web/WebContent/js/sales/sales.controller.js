angular.module('integracion')
    .controller('SalesCtrl', function ($scope, $http, $timeout, $filter, $state, HomeService) {
    	
    	$scope.ventas = [];
    	$scope.title = "Ventas";
    	HomeService.getVentas()
    	.then(function (ventas) {
    		if(ventas.length > 0)
    			$scope.ventas = ventas;
    		else
    			$scope.message = "Usted no posee ninguna venta aun";
        })
        .catch(function (data) {
            //console.log(data);
        });
    	
    	
        $scope.sortBy = function (propertyName) {
            $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
            $scope.propertyName = propertyName;
        };

    	
    });
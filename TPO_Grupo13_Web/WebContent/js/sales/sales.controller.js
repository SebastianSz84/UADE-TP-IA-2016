angular.module('integracion')
    .controller('SalesCtrl', function ($scope, $http, $timeout, $filter, $state, HomeService) {
    	
    	$scope.ventas = [];
    	loadSales();
    	
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
        
        $scope.openDetail = function(item){
        	$scope.isSaleView = false;
        	$scope.details = item.items;
        	$scope.title = "Detalle compra";
        };
        
        $scope.backToSales = function(){
        	loadSales();
        }
        
        $scope.backHome = function(){
        	$state.go('home');
        } 
        
        function loadSales(){
        	$scope.isSaleView = true;
        	$scope.details = [] ;
        	$scope.title = "Compras";
        }
    	
    });
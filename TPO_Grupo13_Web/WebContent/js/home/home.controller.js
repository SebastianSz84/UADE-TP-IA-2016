/**
 * Created by gusta on 
 */
angular.module('integracion')
    .controller('HomeCtrl', function($scope, $http, $rootScope, $timeout) {
    	
    	$scope.successMessage = "";
    	$scope.infoMessage = "";
    	$scope.dangerMessage = "";
    	$scope.user = $rootScope.user;
    	if ($scope.user){
    		$scope.title = "Bienvenido, " +$scope.user.nombre+"!";
    	}
    	else{
    		$scope.title = "Bienvenido!"
    		$scope.user = {
    				id: 1
    		};
    	}
    		
    	$scope.carrito = {
    			"idUsuario": $scope.user.id,
    			"items": []
    	};
    			
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
    
    		$scope.add = function(scope){
    			if(scope.quantity){
    				var subTotal = scope.item.precio * parseInt(scope.quantity);
    	    			$scope.carrito.items.push({
    	    				"cantidad" : scope.quantity,
	    					"producto" : scope.item,
	    					"subTotal": subTotal
    	    			});
    	    	    	$scope.infoMessage = "Item agregado a carrito";
    	    	    	$timeout(function(){
    	    	    		$scope.infoMessage = "";
    	    	    	}, 3000)
    			}
    			else {
	    	    	$scope.infoMessage = "Debe seleccionar cantidad";
	    	    	$timeout(function(){
	    	    		$scope.infoMessage = "";
	    	    	}, 3000)
    			}
    		}
    		
    		$scope.saveCarrito = function(){
    			if($scope.carrito.items.length>0){
	    	    	$http({
	    	            'method':'post',
	    	            'url':'http://localhost:8080/TPO_Grupo13_Web/ServletCarrito',
	                    'params' : {
	                    	'carrito': $scope.carrito
	                    }  
	    			})
	    	        .success(function (data) {
		    	    	$scope.successMessage = data;
		    	    	$timeout(function(){
		    	    		$scope.successMessage = "";
		    	    	}, 3000)
	    	        	
	    	        }).
	    	        error(function (data, status) {
	    	            console.log(data);
	    	            console.log(status);
		    	    	$scope.alertMessage = data;
		    	    	$timeout(function(){
		    	    		$scope.alertMessage = "";
		    	    	}, 3000)
	    	        });
    			}
    			else{
	    	    	$scope.infoMessage = "El carrito esta vacio";
	    	    	$timeout(function(){
	    	    		$scope.infoMessage = "";
	    	    	}, 3000)
    			}
    		}
    		
    		$scope.confirmCarrito = function(){
    			if($scope.carrito.items.length>0){
	    	    	$http({
	    	            'method':'post',
	    	            'url':'http://localhost:8080/TPO_Grupo13_Web/ServletVenta',
	                    'params' : {
	                    	'carrito': $scope.carrito
	                    }  
	    			})
	    	        .success(function (data) {
		    	    	$scope.successMessage = data;
		    	    	$timeout(function(){
		    	    		$scope.successMessage = "";
		    	    	}, 3000)
	    	        	
	    	        }).
	    	        error(function (data, status) {
	    	            console.log(data);
	    	            console.log(status);
		    	    	$scope.alertMessage = data;
		    	    	$timeout(function(){
		    	    		$scope.alertMessage = "";
		    	    	}, 3000)
	    	        });
    			}
    			else{
	    	    	$scope.infoMessage = "El carrito esta vacio";
	    	    	$timeout(function(){
	    	    		$scope.infoMessage = "";
	    	    	}, 3000)
    			}
    		}
    		
    		$scope.removeItem = function(key){
    			$scope.carrito.items.splice(key, 1);
    		}
    		
    		
    	//NO FUNCA NO SE PORQUE TODAVIA
    	$scope.getBestSellers = function(){
        	$http({
                'method':'get',
                'url':'http://localhost:8080/TPO_Grupo13_Web/ListadoBestSellers'
    		})
            .success(function (data) {
            	console.log(data);
            }).
            error(function (data, status) {
                console.log(data);
                console.log(status);
            });

    	}
    });
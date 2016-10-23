/**
 * Created by gusta on 
 */
angular.module('integracion')
    .controller('HomeCtrl', function($scope, $http, $rootScope, $timeout) {
    	
    	$scope.user = $rootScope.user;
    	$scope.carrito = null;
    	if ($scope.user){
    		$scope.title = "Bienvenido, " +$scope.user.nombre+"!";
    	}
    	else{
    		$scope.title = "Bienvenido!"
    		$scope.user = {
    				id: 1
    		};
    	}
    		
    			
    			
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
    	    		if($scope.carrito){
    	    			$scope.carrito.items.push({
    	    				"cantidad" : scope.quantity,
	    					"producto" : scope.item,
	    					"subTotal": subTotal
    	    			})
    	    		}
    	    		else{
    	    			$scope.carrito = {
    	    				"idUsuario": $scope.user.id,
    	    				"items": [{
    	    					"cantidad" : scope.quantity,
    	    					"producto" : scope.item,
    	    					"subTotal": subTotal
    	    				}]
    	    			}
    	    		}
    			}
    			else {
    				alert("debe seleccionar un valor");
    			}
    		}
    		
    		$scope.confirmCart = function(scope){
    			
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
            	$scope.products = data;
            }).
            error(function (data, status) {
                console.log(data);
                console.log(status);
            });

    	}
    });
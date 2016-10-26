/**
 * Created by gusta on
 */
angular.module('integracion')
    .controller('HomeCtrl', function ($scope, $http, $timeout, $filter, LoginService, HomeService) {

        $scope.successMessage = "";
        $scope.infoMessage = "";
        $scope.dangerMessage = "";

        $scope.title = "Bienvenido, " + LoginService.getUser().nombre + "!";

        $scope.carrito = HomeService.getCarrito();

        $scope.products = [];
        HomeService.getProducts()
            .then(function (products) {
                $scope.products = products;
                $scope.allProducts = angular.copy($scope.products);
            })
            .catch(function (data) {
                console.log(data);
            });

        $scope.sortBy = function (propertyName) {
            $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
            $scope.propertyName = propertyName;
        };

        $scope.add = function (scope) {
        	var exists = false;
            angular.forEach($scope.carrito.items, function (item, key) {
                if (angular.equals(item.producto, scope.item)) {
                    item.cantidad = scope.quantity;
                    item.subTotal = item.producto.precio * parseInt(scope.quantity);
                    exists = true;
                }
            });
            if(!exists){
	            var subTotal = scope.item.precio * parseInt(scope.quantity);
	            $scope.carrito.items.push({
	                "cantidad": scope.quantity,
	                "producto": scope.item,
	                "subTotal": subTotal
	            });
            }
            scope.quantity = 0;
            updateCarritoInServer();
        };

        $scope.confirmCarrito = function () {
            if ($scope.carrito.items.length > 0) {
                $http({
                    'method': 'post',
                    'url': 'http://localhost:8080/TPO_Grupo13_Web/ServletVenta',
                    'params': {
                        'carrito': $scope.carrito
                    }
                })
                    .success(function (data) {
                        $scope.successMessage = data;
                        $scope.carrito.items = [];
                        $timeout(function () {
                            $scope.successMessage = "";
                        }, 3000)

                    }).error(function (data, status) {
                    console.log(data);
                    console.log(status);
                    $scope.alertMessage = data;
                    $timeout(function () {
                        $scope.alertMessage = "";
                    }, 3000)
                });
            }
            else {
                $scope.infoMessage = "El carrito esta vacio";
                $timeout(function () {
                    $scope.infoMessage = "";
                }, 3000)
            }
        };

        $scope.removeItem = function (key) {
            $scope.carrito.items.splice(key, 1);
            updateCarritoInServer();
        };

        function updateCarritoInServer() {
            HomeService.sendCarrito()
                .then(function (data) {
                    $scope.successMessage = data;
                    $timeout(function () {
                        $scope.successMessage = "";
                    }, 3000);
                })
                .catch(function (data) {
                    console.log(data);
                    console.log(status);
                    $scope.alertMessage = data;
                    $timeout(function () {
                        $scope.alertMessage = "";
                    }, 3000);
                });
        }
        
        $scope.getBestSellers = function(){
        	$scope.allProducts = angular.copy($scope.products);
        	$scope.products = $filter('filter')( $scope.products, {ranking: "!0"});
        }
        
        $scope.getAll = function(){
        	$scope.products = angular.copy($scope.allProducts);
        }

        $scope.logOut = function () {
            LoginService.logOut();
        };
        
        
    });
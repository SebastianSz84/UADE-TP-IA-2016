/**
 * Created by gusta on
 */
angular.module('integracion')
    .controller('HomeCtrl', function ($scope, $http, $timeout, $filter, $state, LoginService, HomeService) {

        $scope.successMessage = "";
        $scope.infoMessage = "";
        $scope.dangerMessage = "";
        $scope.productoDetalle = null;
        $scope.filters = HomeService.getFilters();

        $scope.title = "Bienvenido, " + LoginService.getUserName() + "!";

        $scope.carrito = HomeService.getCarrito();

        $scope.products = [];
        HomeService.getProducts()
            .then(function (products) {
                $scope.products = products;
            })
            .catch(function (data) {
                //console.log(data);
            });

        $scope.sortBy = function (propertyName) {
            $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
            $scope.propertyName = propertyName;
        };

        $scope.add = function (scope) {
            if (!scope.quantity) {
                errorMessage("Debe seleccionar cantidad");
                return;
            }

            var exists = false;
            angular.forEach($scope.carrito.items, function (item, key) {
                if (angular.equals(item.producto, scope.item)) {
                    item.cantidad = scope.quantity;
                    item.subTotal = item.producto.precio * parseInt(scope.quantity);
                    exists = true;
                }
            });
            if (!exists) {
                var subTotal = scope.item.precio * parseInt(scope.quantity);
                $scope.carrito.items.push({
                    "cantidad": scope.quantity,
                    "producto": scope.item,
                    "subTotal": subTotal
                });
            }
            scope.quantity = null;
            updateCarritoInServer('add');
        };

        $scope.confirmCarrito = function () {
            if ($scope.carrito.items.length === 0) {
                errorMessage("El carrito esta vacio");
                return;
            }

            HomeService.confirmCarrito()
                .then(function (data) {
                    $scope.close();
                    sendMessage(data);
                })
                .catch(function (data) {
                    errorMessage(data);
                });
        };

        $scope.removeItem = function (key) {
            $scope.carrito.items.splice(key, 1);
            updateCarritoInServer('delete');
        };

        function updateCarritoInServer(accion) {
            HomeService.sendCarrito(accion)
                .then(function (data) {
                    sendMessage(data);
                })
                .catch(function (data) {
                    errorMessage(data);
                });
        }

        $scope.getBestSellers = function () {
            $scope.searchProduct = "";
            $scope.products = $filter('filter')($scope.products, {ranking: ""});
        };

        $scope.getAll = function () {
            $scope.searchProduct = "";
            HomeService.getProducts()
                .then(function (products) {
                    $scope.products = products;
                })
                .catch(function (data) {
                    console.log(data);
                });
        };

        $scope.showFilters = function () {
            $scope.filters.show = !$scope.filters.show;
        };

        $scope.openDetail = function (item) {
            $scope.productoDetalle = item;
            $("#myModal").modal();
        };

        $scope.close = function () {
            $scope.productoDetalle = null;
            $("#myModal").modal("hide");
        };

        $scope.logOut = function () {
            LoginService.logOut();
        };

        function errorMessage(msg) {
            $scope.alertMessage = msg;
            $timeout(function () {
                $scope.alertMessage = "";
            }, 3000);
        }

        function sendMessage(msg) {
            $scope.successMessage = msg;
            $timeout(function () {
                $scope.successMessage = "";
            }, 3000);
        }

        $scope.goSales = function () {
            $state.go('sales');
        }

    });
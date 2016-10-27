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
            })
            .catch(function (data) {
                console.log(data);
            });

        $scope.sortBy = function (propertyName) {
            $scope.reverse = ($scope.propertyName === propertyName) ? !$scope.reverse : false;
            $scope.propertyName = propertyName;
        };

        $scope.add = function (scope) {
            if (scope.quantity === 0) {
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
            updateCarritoInServer();
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
            updateCarritoInServer();
        };

        function updateCarritoInServer() {
            HomeService.sendCarrito()
                .then(function (data) {
                    sendMessage(data);
                })
                .catch(function (data) {
                    errorMessage(data);
                });
        }

        $scope.getBestSellers = function () {
            $scope.products = $filter('filter')($scope.products, {ranking: ""});
        };

        $scope.getAll = function () {
            HomeService.getProducts()
                .then(function (products) {
                    $scope.products = products;
                })
                .catch(function (data) {
                    console.log(data);
                });

        };

        $scope.openDetail = function () {
            $scope.isDetail = true;
            $("#myModal").modal();
        };

        $scope.close = function () {
            $scope.isDetail = false;
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

    });
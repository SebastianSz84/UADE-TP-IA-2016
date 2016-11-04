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

        $scope.sorting = {
            'sortBy': 'precio',
            'reverse': false
        };

        $scope.title = "Bienvenidx, " + LoginService.getUserName() + "!";

        $scope.carrito = HomeService.getCarrito();

        $scope.products = [];
        HomeService.getProducts()
            .then(function (products) {
                $scope.products = products;
                sortProducts();
            })
            .catch(function (data) {
                //console.log(data);
            });

        function sortProducts() {
            $scope.sortedProducts = $filter('orderBy')($scope.products, $scope.sorting.sortBy, $scope.sorting.reverse);
        }

        $scope.$watch('sorting.sortBy', function () {
            $scope.sorting.reverse = false;
            sortProducts();
        });

        $scope.$watch('sorting.reverse', function () {
            sortProducts();
        });

        $scope.sortBy = function (propertyName) {
            $scope.sorting.reverse = ($scope.propertyName === propertyName) ? !$scope.sorting.reverse : false;
            $scope.sorting.sortBy = propertyName;
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
        };

    });
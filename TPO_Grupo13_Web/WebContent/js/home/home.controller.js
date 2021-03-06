/**
 * Created by gusta on
 */
angular.module('integracion')
    .controller('HomeCtrl', function ($scope, $http, $timeout, $filter, $state, LoginService, HomeService) {

        $scope.productoDetalle = null;
        $scope.filters = HomeService.getFilters();

        $scope.title = "Bienvenidx, " + LoginService.getUserName() + "!";

        $scope.carrito = HomeService.getCarrito();

        $scope.products = [];
        $scope.filteredProducts = [];

        $scope.getAll = function () {
            $scope.filters.bestSellers = false;
            $scope.searchProduct = "";
            HomeService.getProducts()
                .then(function (products) {
                    $scope.products = products;
                    $scope.filterProducts();
                })
                .catch(function (data) {
                    //console.log(data);
                });
        };
        $scope.getAll();

        $scope.filterProducts = function () {
            $scope.filteredProducts = $filter('productsFilter')($scope.products, $scope.searchProduct);
        };

        $scope.sortBy = function (propertyName) {
            $scope.filters.sorting.reverse = ($scope.propertyName === propertyName) ? !$scope.filters.sorting.reverse : false;
            $scope.filters.sorting.sortBy = propertyName;
        };

        $scope.confirmCarrito = function () {
            if ($scope.carrito.items.length === 0) {
                HomeService.errorMessage("El carrito esta vacio");
                return;
            }
            $scope.isLoading = true;
            HomeService.confirmCarrito()
                .then(function (data) {
                    $scope.close();
                    HomeService.sendMessage(data);
                    $scope.isLoading = false;
                })
                .catch(function (data) {
                    $scope.close();
                    HomeService.errorMessage(data);
                    $scope.isLoading = false;
                });
        };

        $scope.removeItem = function (key) {
        	if(!$scope.isLoading){
            	$scope.isLoading = true;
                $scope.carrito.items.splice(key, 1);
                updateCarritoInServer('delete');
        	}
        };

        function updateCarritoInServer(accion) {
            HomeService.sendCarrito(accion)
                .then(function (data) {
                	$scope.isLoading = false;
                    HomeService.sendMessage(data);
                })
                .catch(function (data) {
                	$scope.isLoading = false;
                    HomeService.errorMessage(data);
                });
        }

        $scope.getBestSellers = function () {
            $scope.searchProduct = "";
            $scope.filters.bestSellers = true;
        };

        $scope.showFilters = function () {
            $scope.filters.show = !$scope.filters.show;
        };

        $scope.openDetail = function (args) {
            $scope.productoDetalle = args;
            $("#myModal").modal();
        };

        $scope.close = function () {
            $scope.productoDetalle = null;
            $("#myModal").modal("hide");
        };

        $scope.logOut = function () {
            LoginService.logOut();
        };

        $scope.goSales = function () {
            $state.go('sales');
        };

    	$scope.$on('openDetails', function(args){
    		$scope.openDetail(args.targetScope.product);
    	});

        $scope.$watchCollection('filters', function () {
            $scope.filterProducts();
        }, true);

        $scope.$watchCollection('filters.sorting', function () {
            $scope.filterProducts();
        }, true);

        $scope.$watchCollection('filters.date', function () {
            $scope.filterProducts();
        }, true);

        $scope.$watchCollection('filters.price', function () {
            $scope.filterProducts();
        }, true);
    });
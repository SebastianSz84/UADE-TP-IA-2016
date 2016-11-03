/**
 * Created by gyauny on 2/11/16.
 */
angular.module('integracion')
    .controller('ProductCtrl', function ($scope, $timeout, HomeService) {

        $scope.getBg = function (url) {
            return {
                'background': 'url("' + url + '")',
                'background-position': 'center',
                'background-size': 'cover'
            };
        };

        $scope.add = function () {
            HomeService.addToCarrito($scope.product);

            $scope.successMessage = "Producto agregado al carrito";
            $timeout(function () {
                $scope.successMessage = "";
            }, 3000);
        };
    });
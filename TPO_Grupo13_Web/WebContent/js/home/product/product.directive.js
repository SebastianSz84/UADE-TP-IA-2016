/**
 * Created by gyauny on 2/11/16.
 */
angular.module('integracion')
    .directive('product', function () {
        return {
            controller: 'ProductCtrl',
            templateUrl: 'js/home/product/product.html',
            replace: true,
            restrict: 'E',
            scope: {
                product: '='
            }
        };
    });
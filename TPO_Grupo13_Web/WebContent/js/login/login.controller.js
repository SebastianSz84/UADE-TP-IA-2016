/**
 * Created by gusta on 23/11/2015.
 */
angular.module('distribuidas')
    .controller('LoginCtrl', function($scope, $rootScope, $state, $http) {
    	$scope.show = false;
    	$scope.checkNumber = function (){
    		$http({
                'method':'get',
                'url':'http://localhost:8080/AD_Cliente_Web/ServletLogin?nroCliente=' +$scope.nroCliente
            })
            .success(function (data) {
                if(data=="true"){
            		$rootScope.nroCliente = $scope.nroCliente;
            		$state.go('home');
                }
                else{
                	$scope.show = true;
                	$scope.nroCliente= '';
                }
            }).
            error(function (data, status) {
                console.log(data);
                console.log(status);
            });

    	};
    });
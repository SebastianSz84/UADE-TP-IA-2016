/**
 * Created by gusta
 */
angular.module('integracion')
    .controller('LoginCtrl', function($scope, $rootScope, $state, $http) {
    	$scope.show = false;
    	$scope.checkNumber = function (){
    		$http({
                'method':'post',
                'url':'http://localhost:8080/TPO_Grupo13_Web/ServletLogin',
                'params' : {
                	'user': $scope.user,        
                	'password': $scope.password
                }         
    		})
            .success(function (data) {
                if(data=="true"){
            		$rootScope.user = $scope.user;
            		$state.go('home');
                }
                else{
                	$scope.show = true;
                	$scope.user= '';
                	$scope.password= '';
                }
            }).
            error(function (data, status) {
                console.log(data);
                console.log(status);
            });

    	};
    });
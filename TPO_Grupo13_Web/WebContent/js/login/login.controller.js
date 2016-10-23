/**
 * Created by gusta
 */
angular.module('integracion')
    .controller('LoginCtrl', function($scope, $rootScope, $state, $http) {
    	$scope.show = false;
    	$scope.errorMessage = "";
    	$scope.user = "veritoblack@gmail.com";
    	$scope.password = 123;
    	$scope.checkNumber = function (){
    		if($scope.user &&  $scope.password){
    		$http({
                'method':'post',
                'url':'http://localhost:8080/TPO_Grupo13_Web/ServletLogin',
                'params' : {
                	'user': $scope.user,        
                	'password': $scope.password
                }         
    		})
            .success(function (data) {
                if(angular.isObject(data)){
            		$rootScope.user = data;
            		$state.go('home');
                }
                else if (angular.isString(data)){
                	$scope.show = true;
                	$scope.errorMessage = data;
                	$scope.user= '';
                	$scope.password= '';
                }
            }).
            error(function (data, status) {
                console.log(data);
                console.log(status);
            });
    		}
    		else{
    			$scope.show = true;
            	$scope.errorMessage = "Complete todos los campos";
    		}
    	};
    });
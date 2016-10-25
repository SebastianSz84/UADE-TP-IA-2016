/**
 * Created by gusta
 */
angular.module('integracion')
    .controller('LoginCtrl', function ($scope, $state, LoginService) {
        $scope.show = false;
        $scope.errorMessage = "";
        $scope.user = "veritoblack@gmail.com";
        $scope.password = 123;
        $scope.checkNumber = function () {
            if ($scope.user && $scope.password) {

                LoginService.login($scope.user, $scope.password)
                    .then(function () {
                        $state.go('home');
                    })
                    .catch(function (data) {
                        $scope.show = true;
                        $scope.errorMessage = data;
                        $scope.user = '';
                        $scope.password = '';
                    });
            }
            else {
                $scope.show = true;
                $scope.errorMessage = "Complete todos los campos";
            }
        };
    });
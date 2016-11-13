/**
 * Created by gyauny on 24/10/16.
 */
angular.module('integracion')
    .service('LoginService', function ($http, $q, $state, $localStorage, Constants) {

        var userData = $localStorage.getObject('user') || {};

        function login(username, password) {
            return $q(function (resolve, reject) {
                $http({
                    'method': 'post',
                    'url': Constants.baseUrl +'TPO_Grupo13_Web/ServletLogin',
                    'params': {
                        'user': username,
                        'password': password
                    }
                })
                    .success(function (data) {
                        if (angular.isObject(data)) {
                            userData = data;
                            $localStorage.setObject('user', userData);
                            resolve();
                        }
                        else if (angular.isString(data)) {
                            reject(data);
                        }
                    })
                    .error(function (data, status) {
                        //console.log(data);
                        //console.log(status);
                    });
            });
        }

        function logOut() {
            userData = {};
            $localStorage.setObject('user', userData);
            $state.go('login');
        }

        function getUser() {
            return userData;
        }

        function getUserName() {
            return userData && userData.nombre;
        }
        
        function getUserId(){
        	return userData.id;
        }

        return {
            login: login,
            logOut: logOut,
            getUser: getUser,
            getUserId: getUserId,
            getUserName: getUserName
        }
    });
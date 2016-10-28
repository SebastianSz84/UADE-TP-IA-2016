/**
 * Created by gyauny on 24/10/16.
 */
angular.module('integracion')
    .service('HomeService', function ($http, $q, $state, $localStorage, LoginService) {

        var carritoData = $localStorage.getObject('carrito') || {
                "idUsuario": LoginService.getUser().id,
                "items": []
            };

        function cargarCarrito() {
            return $q(function (resolve, reject) {
                $http({
                    'method': 'post',
                    'url': 'http://localhost:8080/TPO_Grupo13_Web/ServletGetCarrito',
                    'data': {
                        'user': LoginService.getUserId()
                    }
                })
                    .success(function (data) {
                        if (angular.isObject(data)) {
                            carritoData = data;
                            $localStorage.setObject('user', carritoData);
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

        cargarCarrito();

        function getCarrito() {
            return carritoData;
        }

        function getProducts() {
            return $q(function (resolve, reject) {
                $http({
                    'method': 'get',
                    'url': 'http://localhost:8080/TPO_Grupo13_Web/ListadoProductos'
                })
                    .success(function (data) {
                        angular.forEach(data, function (item, key) {
                            if (item.ranking === 0)
                                item.ranking = null;
                        });
                        resolve(data);
                    })
                    .error(function (data, status) {
                        reject(data);
                    });
            });
        }
        
        function getVentas() {
            return $q(function (resolve, reject) {
                $http({
                    'method': 'get',
                    'url': 'http://localhost:8080/TPO_Grupo13_Web/ServletVenta'
                })
                    .success(function (data) {
                        
                    })
                    .error(function (data, status) {
                        reject(data);
                    });
            });
        }

        function sendCarrito() {
            return $q(function (resolve, reject) {
                $http({
                    'method': 'post',
                    'url': 'http://localhost:8080/TPO_Grupo13_Web/ServletCarrito',
                    'data': carritoData
                })
                    .success(function (data) {
                        resolve(data);
                    })
                    .error(function (data, status) {
                        reject(data);
                    });
            });
        }

        function confirmCarrito() {
            return $q(function (resolve, reject) {
                $http({
                    'method': 'post',
                    'url': 'http://localhost:8080/TPO_Grupo13_Web/ServletVenta',
                    'data': carritoData
                })
                    .success(function (data) {
                        resolve(data);

                    })
                    .error(function (data, status) {
                        reject(data);
                    });
            });
        }

        return {
            getCarrito: getCarrito,
            getProducts: getProducts,
            getVentas: getVentas,
            sendCarrito: sendCarrito,
            confirmCarrito: confirmCarrito
        }
    });
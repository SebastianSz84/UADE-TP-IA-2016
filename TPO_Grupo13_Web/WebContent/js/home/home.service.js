/**
 * Created by gyauny on 24/10/16.
 */
angular.module('integracion')
    .service('HomeService', function ($http, $q, $state, $localStorage, $timeout, LoginService, Constants) {

        var carritoData = {
            "idUsuario": LoginService.getUser().id,
            "items": []
        };

        var filters = {
            show: false,
            bestSellers: false,
            price: {
                min: 0,
                max: null
            },
            date: {
                from: null,
                to: null
            },
            sorting: {
                sortBy: 'precio',
                reverse: false
            },
            messages: {
                success: '',
                info: '',
                danger: ''
            }
        };

        function cargarCarrito() {
            return $q(function (resolve, reject) {
                $http({
                    'method': 'post',
                    'url': Constants.baseUrl +'TPO_Grupo13_Web/ServletGetCarrito',
                    'data': {
                        'userId': LoginService.getUserId()
                    }
                })
                    .success(function (data) {
                        if (angular.isObject(data)) {
                            carritoData.length = 0;
                            angular.forEach(data.items, function(item, key){
                                item.subTotal = item.producto.precio * parseInt(item.cantidad);
                            });
                            angular.copy(data.items, carritoData.items);
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
                    'url': Constants.baseUrl +'TPO_Grupo13_Web/ListadoProductos'
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
                    'url': Constants.baseUrl +'TPO_Grupo13_Web/ServletVenta',
                    'params': {
                        'idUsuario': LoginService.getUserId()
                    }
                })
                    .success(function (data) {
                        resolve(data);
                    })
                    .error(function (data, status) {
                        reject(data);
                    });
            });
        }

        function sendCarrito(accion) {
            return $q(function (resolve, reject) {
                $http({
                    'method': 'post',
                    'url': Constants.baseUrl +'TPO_Grupo13_Web/ServletCarrito',
                    'data': {
                        'accion': accion,
                        'carrito': carritoData
                    }
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
                    'url': Constants.baseUrl +'TPO_Grupo13_Web/ServletVenta',
                    'data': carritoData
                })
                    .success(function (data) {
                        carritoData.items.length = 0;
                        resolve(data);
                    })
                    .error(function (data, status) {
                        reject(data);
                    });
            });
        }

        function addToCarrito(product, quantity) {
            if (!quantity || quantity <= 0) {
                errorMessage("Debe seleccionar cantidad");
                return;
            }

            var exists = false;
            angular.forEach(carritoData.items, function (item, key) {
                if (angular.equals(item.producto, product)) {
                    item.cantidad += quantity;
                    item.subTotal = item.producto.precio * parseInt(item.cantidad);
                    exists = true;
                }
            });

            if (!exists) {
                var subTotal = product.precio * quantity;
                carritoData.items.push({
                    "cantidad": quantity,
                    "producto": product,
                    "subTotal": subTotal
                });
            }
            sendCarrito('add');
            sendMessage('Producto agregado al carrito');
        }

        function getFilters() {
            return filters;
        }

        function errorMessage(msg) {
            filters.messages.alert = msg;
            $timeout(function () {
                filters.messages.alert = "";
            }, 3000);
        }

        function sendMessage(msg) {
            filters.messages.success = msg;
            $timeout(function () {
                filters.messages.success = "";
            }, 3000);
        }

        return {
            getCarrito: getCarrito,
            getProducts: getProducts,
            getVentas: getVentas,
            addToCarrito: addToCarrito,
            sendCarrito: sendCarrito,
            confirmCarrito: confirmCarrito,
            getFilters: getFilters,
            errorMessage: errorMessage,
            sendMessage: sendMessage
        }
    });
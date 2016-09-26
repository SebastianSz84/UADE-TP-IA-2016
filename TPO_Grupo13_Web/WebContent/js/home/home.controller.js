/**
 * Created by gusta on 23/11/2015.
 */
angular.module('distribuidas')
    .controller('HomeCtrl', function($scope, $http, $rootScope, $timeout) {
        $scope.cotizaciones = [];
        $scope.rodamientos = [];
        $scope.armandoCot = false;
        $scope.datos = { cotId:0};

        $scope.verCotizaciones = function(){
            $scope.armandoCot = false;
            $scope.datos.cotId = 0;
            $scope.cotizaciones = [];
            
            /*if ($scope.cotizaciones.length > 0){
            	return;
            }*/
            $http({
                'data': { 'nroCliente' : $rootScope.nroCliente},
                'method':'post',
                'url':'http://localhost:8080/AD_Cliente_Web/ServletListCotizaciones'
            })
            .success(function (data) {
                console.log(data);
                $scope.cotizaciones = data;
            }).
            error(function (data, status) {
                console.log(data);
                console.log(status);
            });
        };

        $scope.armarCotizacion = function(){
            $scope.armandoCot = true;
            
            if ($scope.rodamientos.length > 0){
            	return;
            }
            
            $http({
                'method':'get',
                'url':'http://localhost:8080/AD_Cliente_Web/ServletCrearCot'
            })
            .success(function (data) {
                console.log(data);
                $scope.rodamientos = data;
            }).
            error(function (data, status) {
                $scope.showError('Ocurrio un error.');
                console.log(data);
                console.log(status);
            });
        };
        
        $scope.generarCotizacion = function(rodamientos){
        	var listaItems = [];
        	for (var i=0; rodamientos.length > i; i ++){
        		console.log(rodamientos[i]);
        		if (rodamientos[i].cantidad && Number(rodamientos[i].cantidad) > 0){
        			listaItems.push({ 'codigoSKF':rodamientos[i].codigoSKF, 'cantidad':rodamientos[i].cantidad});
        		}
        	}
        	var data = { 'nroCliente': $rootScope.nroCliente, 'items': listaItems};
        	
        	console.log(data);
        	
        	if (data.items.length == 0){
        		$scope.showError('No se seleccionaron rodamientos.');
        		return;
        	}
        	$http({
        		'data': data,
                'method':'post',
                'url':'http://localhost:8080/AD_Cliente_Web/ServletCrearCot'
            })
            .success(function (data) {
                console.log(data);
                if (data == 'true'){
                	$scope.rodamientos = [];
                	$scope.showMsg('Cotizacion creada.');
                } else {
                	$scope.showError('No se pudo crear la cotizacion.');
                }
            }).
            error(function (data, status) {
                $scope.showError('Ocurrio un error.');
                console.log(data);
                console.log(status);
            });
        };
        
        $scope.aceptarCotizacion = function(cotId){
        	console.log(cotId);
        	var data = { 'nroCliente': $rootScope.nroCliente, 'idCotizacion': cotId};
        	$http({
        		'data': data,
                'method':'post',
                'url':'http://localhost:8080/AD_Cliente_Web/ServletAceptarCot'
            })
            .success(function (data) {
                if (data == 'Aceptada'){
                	$scope.showMsg('Cotizacion Aceptada');
                } else if (data == 'Nueva'){
                	$scope.showError('No se pudo aceptar la cotizacion ya que no se disponen de algunos rodamientos. Se creo una nueva cotizacion con los rodamientos disponibles.');
                } else {
                    $scope.showError('No se pudo aceptar la cotizacion');
                }
            	$scope.verCotizaciones();
            }).
            error(function (data, status) {
                $scope.showError('Ocurrio un error.');
            	$scope.verCotizaciones();
                console.log(data);
                console.log(status);
            });
        };

        $scope.showMsg = function(msg){
            $scope.msg = msg;
            $timeout(function(){
            	$scope.msg = '';
            },5000);
        };

        $scope.showError = function(error){
            $scope.error = error;
            $timeout(function(){
            	$scope.error = '';
            },5000);
        };
        
        $scope.verCotizaciones();
    });
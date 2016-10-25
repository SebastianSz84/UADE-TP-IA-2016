/**
 * Created by gyauny on 20/8/15.
 */

'use strict';
angular.module('integracion')
    .factory('$localStorage', ['$window', function ($window) {
        return {
            set: function (key, value) {
                $window.localStorage[key] = value;
            },

            get: function (key, defaultValue) {
                return $window.localStorage[key] || defaultValue;
            },

            setObject: function (key, value) {
                $window.localStorage[key] = JSON.stringify(value);
            },

            getObject: function (key) {
                if (!$window.localStorage[key])
                    return;
                return JSON.parse($window.localStorage[key]);
            }
        }
    }]);

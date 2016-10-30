/**
 * Created by gyauny on 30/10/16.
 */
angular.module('integracion')
    .filter('productsFilter', function (HomeService) {

        var filters = HomeService.getFilters();

        function coincidence(item, search) {
            return (item.nombre && item.nombre.toLowerCase().search(search.toLowerCase()) >= 0)
                || (item.marca && item.marca.toLowerCase().search(search.toLowerCase()) >= 0);
        }

        return function (items, search, filter) {
            var filtered = [];

            for (var i = 0; i < items.length; i++) {
                var item = items[i];

                if (!item.precio || (filters.price.min && item.precio < filters.price.min) || (filters.price.max && item.precio > filters.price.max)) {
                    continue;
                }

                var itemDate = new Date(item.fechaAlta);
                if (!itemDate || (filters.date.from && itemDate < filters.date.from) || (filters.date.to && itemDate > filters.date.to)) {
                    continue;
                }

                if (!search || coincidence(item, search)) {
                    filtered.push(item);
                }
            }

            return filtered;
        };
    });
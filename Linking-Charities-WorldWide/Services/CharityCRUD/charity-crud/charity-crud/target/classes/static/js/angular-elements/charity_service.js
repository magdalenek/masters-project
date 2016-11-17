'use strict';

App.factory('Charity', ['$resource', function ($resource) {
    return $resource(
        '/charity/:id',
        {id: '@id'},
        {
            update: {
                method: 'PUT'
            }

        }
    );
}]);
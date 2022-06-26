angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8181/orders/rest_api/v1';

    $scope.fillTable = function () {
        $http.get(contextPath )
            .then(function (response) {
                $scope.OrdersList = response.data;
            });
    };

    $scope.deleteOrderById = function (id) {
        $http.delete(contextPath + "/" + id)
            .then(function (response) {
                $scope.fillTable()
            });
    };

    $scope.createNewOrder = function () {
        $http.post(contextPath, $scope.newOrder)
            .then(function (response) {
                $scope.newOrder = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();

});
angular.module('change', []).controller('indexController2', function ($scope, $http) {
    const contextPath = 'http://localhost:8181/orders/rest_api/v1';

    $scope.getOrder = function (id) {
        $http.get(contextPath + "/" + id)
            .then(function (response) {
                $scope.GetOrderTitle = response.data.title;
                $scope.GetOrderText = response.data.text;
                $scope.GetOrderComment = response.data.comment;
            });
    };


    $scope.changeOrder = function () {
        $http.put(contextPath, $scope.updateOrder, $scope.getOrder)
            .then(function (response) {
                $scope.updateOrder = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();

});
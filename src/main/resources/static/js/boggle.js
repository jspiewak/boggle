boggle = angular.module('boggle', []);

boggle.controller('BoggleController', function ($scope, $http) {
    $scope.board = [['r','s','t','u'],['d','i','o','b'],['o','g','g','l'],['e','f','u','n']];

    $scope.reset = function() {
        $scope.board = [['','','',''],['','','',''],['','','',''],['','','','']];
    };

    $scope.solve = function() {
        console.log("Solving!");
        $http.post('/board', $scope.board)
            .then(function(response) {
                console.log("Got " + response.status);
                $scope.words = response.data;
            }, function(response) {
                console.log("Failed with " + response.status);
            });
    };
});

/**
 * Created by Vladislav on 5/29/2016.
 */
app.controller('ReportController', ['$scope', 'UserModel', 'AuthService', '$routeParams', 'AccessValidator', function($scope, userModel, authService, $routeParams, accessValidator){
    accessValidator.redirectIfNoAccess();

    $scope.showExpenses = function(){
        $scope.expenses = true;
        $scope.transactions = false;
    };
    $scope.showTransactions = function(){
        $scope.expenses = false;
        $scope.transactions = true;
    };

    $scope.user = authService.GetUserCredentials();
    userModel.getEvent(user.username, $routeParams.id)
        .success(function(data){
            $scope.event = data;
        });

    userModel.getEventReport($scope.user.username, $routeParams.id).success(function(data){
        $scope.report = data;
    });

    $scope.showExpenses();


}]);

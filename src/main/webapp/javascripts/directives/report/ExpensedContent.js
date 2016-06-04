/**
 * Created by Vladislav on 6/3/2016.
 */

app.directive('expensesContent', ['UserModel', '$route', function(userModel){
    return{
        restrict: 'E',
        scope: false,
        templateUrl: 'javascripts/directives/report/ExpensesContent.html',
        link: function($scope){

            $scope.expenseNew = {};

            $scope.addExpense = function(){
                userModel.addExpense($scope.user, $scope.event, $scope.expenseNew)
                    .success(function(data){
                        $scope.report.expenses.push(data);
                        refreshReport();
                        $scope.expenseNew = {};
                    });
            };

            $scope.deleteExpense = function(expense){
                userModel.deleteExpense($scope.user, $scope.event, expense)
                    .success(function(){
                        var index = $scope.report.expenses.indexOf(expense);
                        $scope.report.expenses.splice(index, 1);
                        refreshReport();
                    });
            };

            function refreshReport(){
                userModel.getEventReport($scope.user.username, $scope.event.eventId)
                    .success(function(data){
                        $scope.report = Object.assign($scope.report, data);
                    });
            }
        }
    }
}]);
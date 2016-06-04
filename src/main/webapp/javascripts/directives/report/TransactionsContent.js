/**
 * Created by Vladislav on 6/3/2016.
 */

app.directive('transactionsContent', function(){
    return{
        restrict: 'E',
        scope:{
            transactions: '='
        },
        templateUrl: 'javascripts/directives/report/TransactionsContent.html'
    }
});
/**
 * Created by Vladislav on 6/3/2016.
 */


app.directive('eventThumbnail', ['UserModel', 'AuthService', '$route', function(userModel, authService, $route){
    return{
        restrict: 'E',
        scope:{
            event: '=',
            index: '='
        },
        templateUrl: 'javascripts/directives/EventThumbnail.html',
        link: function($scope, element, attr){
            $scope.deleteEvent = function(){

                user = authService.GetUserCredentials();
                userModel.deleteEvent(user, $scope.event)
                    .success(function(){
                        var index = $scope.$parent.events.indexOf($scope.expense);
                        $scope.$parent.events.splice($scope.index, 1);
                    });
            }
        }
    }
}]);
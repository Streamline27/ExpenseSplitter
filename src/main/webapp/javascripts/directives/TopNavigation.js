/**
 * Created by Vladislav on 6/3/2016.
 */

app.directive('topNavigation', ['AuthService', '$location', '$route', function(AuthService, $location, $route){
    return{
        restrict: 'E',
        scope:{
        },
        templateUrl: 'javascripts/directives/TopNavigation.html',
        link: function($scope, element, attr){

            AuthService.OnAuthenticationStatusChanged(function(){
                if(AuthService.IsLoggedIn()){

                    $scope.loggedIn = true;
                    $scope.user = AuthService.GetUserCredentials();
                }
                else {

                    $scope.loggedIn = false;
                    $scope.user = null;
                }
            });

            $scope.isActive = function (viewLocation) {
                return viewLocation === $location.path();
            };

            $scope.logout = function(){
                AuthService.Logout();
                $route.reload();
            }
        }
    }
}]);
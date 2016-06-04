/**
 * Created by Vladislav on 6/4/2016.
 */

app.service('AccessValidator', ['$location', 'AuthService', function($location, AuthService){
    this.redirectIfNoAccess = function(){
        if(!AuthService.IsLoggedIn()) $location.path('/auth');
    }

}]);
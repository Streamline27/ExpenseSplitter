/**
 * Created by Vladislav on 6/2/2016.
 */

app.factory('AuthService', AuthService);

function AuthService($http, $cookieStore, $rootScope) {
    AuthService.$inject = ['$http', '$cookieStore', '$rootScope'];
    $rootScope.userCredentials = {};
    var service = {};

    service.GetUserCredentials = GetUserCredentials;
    service.Login = Login;
    service.Logout = Logout;
    service.IsLoggedIn = IsLoggedIn;
    service.OnAuthenticationStatusChanged = OnAuthenticationStatusChanged;
    service.AuthentifyFromCookies = AuthentifyFromCookies;
    service.ClearCookies = ClearCookies;

    return service;

    function Login(username, password, onSuccess, onError) {

        $http({
            method: 'POST',
            url: 'api/user/login',
            headers: { authorization : "Basic "+ btoa(username + ":" + password) }

        }).success(function (data) {
            user = {};
            user.username= username;
            user.password = password;
            user.firstName = data.firstName;
            user.lastName = data.lastName;

            $rootScope.userCredentials = user;

            $cookieStore.put('userCredentials', $rootScope.userCredentials); // save credentials in cookies
            $http.defaults.headers.common['Authorization'] = "Basic "+ btoa(username + ":" + password); // headers for every

            onSuccess && onSuccess()
        }).error(function(){
            if (IsLoggedIn()) ClearCookies();
            onError && onError();
        })
    }

    function Logout() {

        ClearCookies();

        $http({
            method: 'POST',
            url: 'api/user/logout'
        }).success(function(){}).error(function(){});
    }

    function ClearCookies(){
        $rootScope.userCredentials = undefined; // Clear globals
        $cookieStore.remove('userCredentials'); // Clear cookies
        delete $http.defaults.headers.common['Authorization']; //Clear headers
    }

    function GetUserCredentials(){
        return $rootScope.userCredentials;
    }

    function IsLoggedIn(){
        if ($rootScope.userCredentials){
            var size = Object.keys($rootScope.userCredentials).length;
            if(size!=0) return true;
        }
        else return false;
    }

    function OnAuthenticationStatusChanged(callback){
        $rootScope.$watch('userCredentials', callback);
    }

    function AuthentifyFromCookies(){
        userCredentials = $cookieStore.get('userCredentials');

        if(undefined == userCredentials) return;

        var hasFields = Object.keys(userCredentials).length !=0;

        if (hasFields) {
            var username = userCredentials.username;
            var password = userCredentials.password;
            Login(username, password)
        }
    }
}
/**
 * Created by Vladislav on 6/2/2016.
 */

app.controller('AuthorizationController', ['$scope', 'AuthService', '$rootScope','UserModel', '$location',
    function($scope, authService, $rootScope, userModel, $location){

    $scope.showLoginForm = function(){
        $scope.showLogin = true;
        $scope.showRegister = false;
        $scope.user = {};
        $scope.showError = false;
        $scope.errorMsg = "";
        $scope.showInfo = false;
        $scope.infoMsg = "";
    };

    $scope.showRegisterForm = function showRegisterForm(){
        $scope.showRegister = true;
        $scope.showLogin = false;
        $scope.user = {};
        $scope.showError = false;
        $scope.errorMsg = "";
        $scope.showInfo = false;
        $scope.infoMsg = "";
    };

    $scope.login = function(){
        onSuccess = function(){
            $location.path('/');
        };
        onError = function(){
            showLoginError();
        };
        authService.Login($scope.user.username, $scope.user.password, onSuccess, onError);
    };

    $scope.register = function(){
        userModel.registerUser($scope.user).success(function(){
            $scope.showLoginForm();
            showNowLoginMessage();
        }).error(function(){
            showRegisterError();
        });
    };

    $scope.showLoginForm();
    $scope.user = {};

    function showLoginError(){
        $scope.showError = true;
        $scope.errorMsg = "Unable to login. Bad credentials";
    }

    function showRegisterError(){
        $scope.showError = true;
        $scope.errorMsg = "Unable to register. Bad credentials";
    }

    function showNowLoginMessage(){
        $scope.showInfo = true;
        $scope.infoMsg = "You successfully registered. Now sign in.";
    }
}]);
/**
 * Created by Vladislav on 6/1/2016.
 */

app.controller('EventsController', ['$scope', 'UserModel', 'AuthService', 'AccessValidator',
    function($scope, userModel, authService, accessValidator){

    accessValidator.redirectIfNoAccess(); // This page is secured now!

    user = authService.GetUserCredentials();
    eventsAreLoading = true;
    haveEvents = false;
    showUserEvents(user);

    $scope.eventNew = {};

    $scope.addEvent= function(){
        userModel.addEvent(user, $scope.eventNew)
            .success(function(data){
               $scope.events.push(data);
               $scope.eventNew = {};

                if($scope.events.length > 0) $scope.haveEvents = true;
                else haveEvents = false;
            });
    };


    function showUserEvents(user){
         userModel.getEvents(user.username).success(function(data){
             eventsAreLoading = false;
             $scope.events = data;


             if($scope.events.length > 0) $scope.haveEvents = true;
             else haveEvents = false;
        })

    }

}]);
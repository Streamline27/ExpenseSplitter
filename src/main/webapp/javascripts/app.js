/**
 * Created by Vladislav on 5/29/2016.
 */
var app = angular.module('ExpenseSplitter', ['ngRoute', 'ngCookies']);

app.config(function($routeProvider){
    $routeProvider
        .when('/', {
            controller: 'EventsController',
            templateUrl: 'views/events.html'
        })
        .when('/auth', {
            controller: 'AuthorizationController',
            templateUrl: 'views/authorization.html'
        })
        .when('/event/:id/report', {
            controller: 'ReportController',
            templateUrl: 'views/report.html'
        })
        .otherwise({
            redirectTo: '/event'
        });
});

app.run(['AuthService', '$rootScope', '$cookieStore', function(AuthService) {
    // keep user logged in after page refresh

    AuthService.AuthentifyFromCookies();
}]);

app.filter('setDecimal', function ($filter) {
    return function (input, places) {
        if (isNaN(input)) return input;
        // If we want 1 decimal place, we want to mult/div by 10
        // If we want 2 decimal places, we want to mult/div by 100, etc
        // So use the following to create that factor
        var factor = "1" + Array(+(places > 0 && places + 1)).join("0");
        return Math.round(input * factor) / factor;
    };
});
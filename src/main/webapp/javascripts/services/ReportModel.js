/**
 * Created by Vladislav on 6/2/2016.
 */

app.service('ReportModel', ['$http', function($http) {

    this.get = function(eventId){
        return $http.get("http://localhost:8080/api/event/" + eventId + "/report");
    };


}]);
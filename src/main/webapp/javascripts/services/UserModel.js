/**
 * Created by Vladislav on 6/3/2016.
 */

app.service('UserModel', ['$http', function($http){

    this.get = function(username){
        return $http.get("api/user/"+username);
    };

    this.getEvents = function(username){
        return $http.get("api/user/"+username+"/event");
    };

    this.getEvent = function(username, eventId){
        return $http.get("api/user/"+username+"/event/"+eventId);
    };

    this.getEventReport = function(username, eventId){
        return $http.get("api/user/"+username+"/event/"+eventId+"/report");
    };

    this.registerUser = function(user){
        return $http.post("api/user/register", user);
    };

    this.addEvent = function(user, event){
        return $http.post("api/user/" + user.username + "/event", event);
    };

    this.deleteEvent = function(user, event){
        return $http.delete("api/user/" + user.username + "/event/"+event.eventId);
    };

    this.addExpense = function(user, event, expense){
        //alert("api/user/" + user.username + "/event/" + event.eventId);
        return $http.post("api/user/" + user.username + "/event/" + event.eventId+"/expense", expense);
    };

    this.deleteExpense = function(user, event, expense){
        return $http.delete("api/user/" + user.username + "/event/" + event.eventId+"/expense/"+expense.expenseId);
    }


}]);
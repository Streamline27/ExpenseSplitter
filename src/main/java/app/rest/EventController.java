package app.rest;

import app.core.database.EventDAO;
import app.core.database.UserDAO;
import app.core.domain.Event;
import app.core.domain.Expense;
import app.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */

@RestController
@RequestMapping("/api/user/{username}/event")
public class EventController {

    @Autowired private UserDAO userDAO;
    @Autowired private EventDAO eventDAO;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Event> post(@PathVariable String username, @RequestBody Event event){

        User user = userDAO.findOne(username);
        event.setUser(user);

        event = eventDAO.save(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(path = "/{eventId}" ,method = RequestMethod.GET)
    public ResponseEntity<Event> get(@PathVariable Long eventId){

        Event event = eventDAO.findOne(eventId);


        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @RequestMapping(path = "/{eventId}" ,method = RequestMethod.DELETE)
    public HttpStatus delete(@PathVariable Long eventId){

        eventDAO.delete(eventId);
        return HttpStatus.OK;


    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Event>> getAll(@PathVariable String username){

        User user = userDAO.findOne(username);

        List<Event> events = user.getEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

}

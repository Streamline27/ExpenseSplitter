package app.rest;

import app.core.commands.CommandExecutor;
import app.core.commands.commands.events.create.CreateEventCommand;
import app.core.commands.commands.events.create.CreateEventResult;
import app.core.commands.commands.events.delete.DeleteEventCommand;
import app.core.commands.commands.events.delete.DeleteEventResult;
import app.core.commands.commands.events.get.GetEventCommand;
import app.core.commands.commands.events.get.GetEventResult;
import app.core.commands.commands.events.getall.GetUserEventsCommand;
import app.core.commands.commands.events.getall.GetUserEventsResult;
import app.dto.EventDTO;
import app.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav on 6/1/2016.
 */

@RestController
@RequestMapping("/api/user/{username}/event")
public class EventController {

    @Autowired private AuthenticationService authenticationService;
    @Autowired private CommandExecutor executor;



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<EventDTO> post(@PathVariable String username, @RequestBody EventDTO eventDTO){

        if (!authenticationService.isLoggedIn(username)) return getAccessDeniedResponseEntity();


        CreateEventCommand command = new CreateEventCommand(eventDTO, username);
        CreateEventResult result = executor.execute(command);

        eventDTO = result.getEventDTO();
        return new ResponseEntity<>(eventDTO, HttpStatus.OK);

    }



    @RequestMapping(path = "/{eventId}" ,method = RequestMethod.GET)
    public ResponseEntity<EventDTO> get(@PathVariable String username, @PathVariable Long eventId){

        if (!authenticationService.isLoggedIn(username)) return getAccessDeniedResponseEntity();


        GetEventCommand command = new GetEventCommand(eventId);
        GetEventResult result = executor.execute(command);

        EventDTO eventDTO = result.getEventDTO();
        return new ResponseEntity<>(eventDTO, HttpStatus.OK);

    }



    @RequestMapping(path = "/{eventId}" ,method = RequestMethod.DELETE)
    public ResponseEntity<EventDTO> delete(@PathVariable String username, @PathVariable Long eventId){

        if (!authenticationService.isLoggedIn(username)) return getAccessDeniedResponseEntity();


        DeleteEventCommand command = new DeleteEventCommand(eventId);
        DeleteEventResult result = executor.execute(command);

        EventDTO event = result.getEventDTO();
        return new ResponseEntity<>(event, HttpStatus.OK);

    }

    private ResponseEntity<EventDTO> getAccessDeniedResponseEntity() {
        return new ResponseEntity<>(new EventDTO(), HttpStatus.BAD_REQUEST);
    }



    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EventDTO>> getAll(@PathVariable String username){

        if (!authenticationService.isLoggedIn(username))
            return new ResponseEntity<List<EventDTO>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);


        GetUserEventsCommand command = new GetUserEventsCommand(username);
        GetUserEventsResult result = executor.execute(command);

        List<EventDTO> eventDTOs = result.getEvents();
        return new ResponseEntity<>(eventDTOs, HttpStatus.OK);

    }

}

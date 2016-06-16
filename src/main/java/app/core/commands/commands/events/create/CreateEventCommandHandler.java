package app.core.commands.commands.events.create;

import app.core.commands.DomainCommandHandler;
import app.dto.EventDTO;
import app.core.database.EventDAO;
import app.core.database.UserDAO;
import app.core.domain.Event;
import app.core.domain.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static app.core.services.Converter.*;

/**
 * Created by Vladislav on 6/4/2016.
 */

@Component
public class CreateEventCommandHandler implements DomainCommandHandler<CreateEventCommand, CreateEventResult> {

    @Autowired private UserDAO userDAO;
    @Autowired private EventDAO eventDAO;

    @Override
    public CreateEventResult execute(CreateEventCommand command) {

        String username   = command.getUsername();
        EventDTO eventDTO = command.getEventDTO();

        UserCredentials userCredentials = userDAO.findOne(username);

        Event event = new Event(eventDTO.getEventName());
        event.setUserCredentials(userCredentials);
        event = eventDAO.save(event);

        return new CreateEventResult(convert(event, TO_EVENT_DTO));
    }


    @Override
    public Class getCommandType() {
        return CreateEventCommand.class;
    }

}

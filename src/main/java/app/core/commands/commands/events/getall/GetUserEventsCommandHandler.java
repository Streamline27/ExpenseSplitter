package app.core.commands.commands.events.getall;

import app.core.commands.DomainCommandHandler;
import app.core.domain.Event;
import app.core.database.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static app.core.services.Converter.*;

/**
 * Created by Vladislav on 6/4/2016.
 */

@Component
public class GetUserEventsCommandHandler implements DomainCommandHandler<GetUserEventsCommand, GetUserEventsResult>{

    @Autowired private UserDAO userDAO;

    @Override
    public GetUserEventsResult execute(GetUserEventsCommand command) {

        String username = command.getUsername();
        List<Event> events = userDAO.findOne(username).getEvents();

        return new GetUserEventsResult(convert(events, TO_EVENT_DTO));
    }


    @Override
    public Class getCommandType() {
        return GetUserEventsCommand.class;
    }


}

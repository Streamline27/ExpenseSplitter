package app.core.commands.commands.events.delete;

import app.core.commands.DomainCommandHandler;
import app.core.database.EventDAO;
import app.core.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static app.core.services.Converter.*;

/**
 * Created by Vladislav on 6/4/2016.
 */

@Component
public class DeleteEventCommandHandler implements DomainCommandHandler<DeleteEventCommand, DeleteEventResult>{

    @Autowired private EventDAO eventDAO;

    @Override
    public DeleteEventResult execute(DeleteEventCommand command) {

        Long eventId = command.getId();

        Event event = eventDAO.findOne(eventId);
        eventDAO.delete(eventId);

        return new DeleteEventResult(convert(event, TO_EVENT_DTO));
    }

    @Override
    public Class getCommandType() {
        return DeleteEventCommand.class;
    }
}

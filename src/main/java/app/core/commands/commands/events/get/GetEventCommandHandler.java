package app.core.commands.commands.events.get;

import app.core.commands.DomainCommandHandler;
import app.core.database.EventDAO;
import app.core.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static app.core.services.Converter.TO_EVENT_DTO;
import static app.core.services.Converter.convert;


/**
 * Created by Vladislav on 6/5/2016.
 */
@Component
public class GetEventCommandHandler implements DomainCommandHandler<GetEventCommand, GetEventResult> {

    @Autowired private EventDAO eventDAO;

    @Override
    public GetEventResult execute(GetEventCommand command) {

            Event event = eventDAO.findOne(command.getEventId());
            return new GetEventResult(convert(event, TO_EVENT_DTO));
    }


    @Override
    public Class getCommandType() {
        return GetEventCommand.class;
    }

}

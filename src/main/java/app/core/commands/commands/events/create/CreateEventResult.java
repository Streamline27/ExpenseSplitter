package app.core.commands.commands.events.create;

import app.core.commands.DomainCommandResult;
import app.dto.EventDTO;

/**
 * Created by Vladislav on 6/4/2016.
 */
public class CreateEventResult implements DomainCommandResult {
    private EventDTO eventDTO;

    public CreateEventResult() {
    }



    public CreateEventResult(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }
}

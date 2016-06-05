package app.core.commands.commands.events.delete;

import app.core.commands.DomainCommandResult;
import app.dto.EventDTO;

/**
 * Created by Vladislav on 6/4/2016.
 */
public class DeleteEventResult implements DomainCommandResult {
    private EventDTO eventDTO;

    public DeleteEventResult(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }
}

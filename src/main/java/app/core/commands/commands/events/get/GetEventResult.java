package app.core.commands.commands.events.get;

import app.core.commands.DomainCommandResult;
import app.dto.EventDTO;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class GetEventResult implements DomainCommandResult {
    private EventDTO eventDTO;

    public GetEventResult() {
    }

    public GetEventResult(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }
}

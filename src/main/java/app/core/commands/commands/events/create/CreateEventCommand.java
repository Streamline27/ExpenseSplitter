package app.core.commands.commands.events.create;

import app.core.commands.DomainCommand;
import app.dto.EventDTO;

/**
 * Created by Vladislav on 6/4/2016.
 */
public class CreateEventCommand implements DomainCommand<CreateEventResult> {
    private EventDTO eventDTO;
    private String username;

    public CreateEventCommand() {
    }

    public CreateEventCommand(EventDTO eventDTO, String username) {
        this.eventDTO = eventDTO;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public EventDTO getEventDTO() {
        return eventDTO;
    }

    public void setEventDTO(EventDTO eventDTO) {
        this.eventDTO = eventDTO;
    }
}

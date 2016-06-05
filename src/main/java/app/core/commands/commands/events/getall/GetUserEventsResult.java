package app.core.commands.commands.events.getall;

import app.core.commands.DomainCommandResult;
import app.dto.EventDTO;

import java.util.List;

/**
 * Created by Vladislav on 6/4/2016.
 */
public class GetUserEventsResult implements DomainCommandResult {
    private List<EventDTO> events;

    public GetUserEventsResult() {
    }

    public GetUserEventsResult(List<EventDTO> events) {
        this.events = events;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventDTO> events) {
        this.events = events;
    }
}

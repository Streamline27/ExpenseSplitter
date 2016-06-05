package app.core.commands.commands.events.get;

import app.core.commands.DomainCommand;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class GetEventCommand implements DomainCommand<GetEventResult> {
    private Long eventId;

    public GetEventCommand() {
    }

    public GetEventCommand(Long eventId) {
        this.eventId = eventId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}

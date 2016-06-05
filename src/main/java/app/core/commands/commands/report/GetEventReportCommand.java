package app.core.commands.commands.report;

import app.core.commands.DomainCommand;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class GetEventReportCommand implements DomainCommand<GetEventReportResult> {
    private Long eventId;

    public GetEventReportCommand(Long eventId) {
        this.eventId = eventId;
    }

    public GetEventReportCommand() {
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}

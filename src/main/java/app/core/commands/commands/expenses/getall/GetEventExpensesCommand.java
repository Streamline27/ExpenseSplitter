package app.core.commands.commands.expenses.getall;


import app.core.commands.DomainCommand;

/**
 * Created by Vladislav on 6/5/2016.
 */
public class GetEventExpensesCommand implements DomainCommand<GetEventExpensesResult> {
    private Long eventId;

    public GetEventExpensesCommand(Long eventId) {
        this.eventId = eventId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}

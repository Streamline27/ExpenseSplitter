package app.core.commands.commands.events.getall;

import app.core.commands.DomainCommand;

/**
 * Created by Vladislav on 6/4/2016.
 */
public class GetUserEventsCommand implements DomainCommand<GetUserEventsResult> {
    private String username;

    public GetUserEventsCommand() {
    }



    public GetUserEventsCommand(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

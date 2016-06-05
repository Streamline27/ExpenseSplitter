package app.core.commands.commands.events.delete;

import app.core.commands.DomainCommand;

/**
 * Created by Vladislav on 6/4/2016.
 */
public class DeleteEventCommand implements DomainCommand<DeleteEventResult> {
    private Long id;

    public DeleteEventCommand() {
    }

    public DeleteEventCommand(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

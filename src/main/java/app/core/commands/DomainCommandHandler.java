package app.core.commands;

/**
 * Created by Vladislav on 6/4/2016.
 */
public interface DomainCommandHandler<C extends DomainCommand<R>, R extends DomainCommandResult> {

    R execute(C command);

    Class getCommandType();

}

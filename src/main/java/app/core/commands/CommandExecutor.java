package app.core.commands;

/**
 * Created by Vladislav on 6/4/2016.
 */
public interface CommandExecutor {
    <T extends DomainCommandResult> T execute(DomainCommand<T> command);
}

package app.core.commands.commands.registration;

import app.core.commands.DomainCommandResult;
import app.core.commands.commands.registration.results.BadCredentialsException;
import app.dto.UserInfoDTO;

/**
 * Created by Vladislav on 6/4/2016.
 */
public interface RegisterUserResult extends DomainCommandResult {
    UserInfoDTO getRegisteredUser() throws BadCredentialsException;

}

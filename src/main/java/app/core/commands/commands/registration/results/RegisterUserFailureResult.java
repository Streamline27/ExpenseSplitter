package app.core.commands.commands.registration.results;

import app.core.commands.commands.registration.RegisterUserResult;
import app.dto.UserInfoDTO;

/**
 * Created by Vladislav on 6/4/2016.
 */
public class RegisterUserFailureResult implements RegisterUserResult {
    @Override
    public UserInfoDTO getRegisteredUser() throws BadCredentialsException {
        throw new BadCredentialsException();
    }
}

package app.core.commands.commands.registration.results;

import app.core.commands.commands.registration.RegisterUserResult;
import app.dto.UserInfoDTO;

/**
 * Created by Vladislav on 6/4/2016.
 */
public class RegisterUserSuccessResult implements RegisterUserResult {
    private UserInfoDTO userInfoDTO;

    public RegisterUserSuccessResult(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }

    public UserInfoDTO getUserInfoDTO() {
        return userInfoDTO;
    }

    public void setUserInfoDTO(UserInfoDTO userInfoDTO) {
        this.userInfoDTO = userInfoDTO;
    }

    @Override
    public UserInfoDTO getRegisteredUser() throws BadCredentialsException{
        return userInfoDTO;
    }
}

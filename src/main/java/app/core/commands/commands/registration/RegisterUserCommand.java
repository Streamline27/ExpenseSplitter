package app.core.commands.commands.registration;

import app.core.commands.DomainCommand;
import app.dto.UserDTO;

/**
 * Created by Vladislav on 6/4/2016.
 */
public class RegisterUserCommand implements DomainCommand<RegisterUserResult> {

    private UserDTO userDTO;

    public RegisterUserCommand(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}

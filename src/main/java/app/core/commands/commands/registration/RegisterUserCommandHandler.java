package app.core.commands.commands.registration;


import app.core.commands.DomainCommandHandler;
import app.core.commands.commands.registration.results.RegisterUserFailureResult;
import app.core.commands.commands.registration.results.RegisterUserSuccessResult;
import app.dto.UserDTO;
import app.core.database.UserDAO;
import app.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static app.core.services.Converter.*;

/**
 * Created by Vladislav on 6/4/2016.
 */

@Component
public class RegisterUserCommandHandler implements DomainCommandHandler<RegisterUserCommand, RegisterUserResult> {

    @Autowired private UserDAO userDAO;

    @Override
    public RegisterUserResult execute(RegisterUserCommand command) {
        if (command.getUserDTO()==null) return new RegisterUserFailureResult();

        UserDTO userDTO = command.getUserDTO();
        String username = userDTO.getUsername();

        if (userIsAlreadyRegistered(username)){

            User user = convert(userDTO, FROM_USER_DTO);
            user = userDAO.save(user);

            return new RegisterUserSuccessResult(convert(user, TO_USER_INFO_DTO));
        }
        else return new RegisterUserFailureResult();


    }

    private boolean userIsAlreadyRegistered(String username) {
        return !userDAO.exists(username);
    }

    @Override
    public Class getCommandType() {
        return RegisterUserCommand.class;
    }
}

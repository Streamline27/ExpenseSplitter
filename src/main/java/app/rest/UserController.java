package app.rest;

import app.core.commands.CommandExecutor;
import app.core.commands.commands.registration.results.BadCredentialsException;
import app.core.commands.commands.registration.RegisterUserCommand;
import app.core.commands.commands.registration.RegisterUserResult;
import app.dto.UserDTO;
import app.dto.UserInfoDTO;
import app.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Vladislav on 6/3/2016.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired private AuthenticationService authenticationService;
    @Autowired private CommandExecutor commandExecutor;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<UserInfoDTO> registerUser(@RequestBody UserDTO userDTO){

        RegisterUserCommand command = new RegisterUserCommand(userDTO);
        RegisterUserResult result = commandExecutor.execute(command);

        try {
            UserInfoDTO userInfoDTO = result.getRegisteredUser();
            return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);

        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new UserInfoDTO(), HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<UserInfoDTO> getCurrentUser(){
        return new ResponseEntity<>(authenticationService.getAuthorizedUser(), HttpStatus.OK);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserInfoDTO> login(){
        return new ResponseEntity<>(authenticationService.getAuthorizedUser(), HttpStatus.OK);
    }


}

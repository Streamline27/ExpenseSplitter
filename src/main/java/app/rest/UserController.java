package app.rest;

import app.core.database.UserDAO;
import app.core.domain.Event;
import app.core.domain.User;
import app.core.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Vladislav on 6/3/2016.
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired private UserDAO userDAO;


    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<User> get(@PathVariable String username){
        User user = userDAO.findOne(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<User> post(@RequestBody User user){
        if (!userDAO.exists(user.getUsername())){
            userDAO.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else return new ResponseEntity<>(new User(), HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<User> getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return new ResponseEntity<>(((UserPrincipal)principal).getUser(), HttpStatus.OK);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return new ResponseEntity<>(((UserPrincipal)principal).getUser(), HttpStatus.OK);
    }


}

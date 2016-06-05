package app.security;

import app.dto.UserInfoDTO;
import app.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by Vladislav on 6/5/2016.
 */

@Component
public class AuthenticationService {
    public boolean isLoggedIn(String username) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return ((UserPrincipal)principal).getUser().getUsername().equals(username);

    }

    public UserInfoDTO getAuthorizedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        return ((UserPrincipal)principal).getUser();
    }
}

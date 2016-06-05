package app.security;

import app.core.domain.User;
import app.dto.UserInfoDTO;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Vladislav on 2/25/2016.
 */
public class UserPrincipal extends org.springframework.security.core.userdetails.User {
    private UserInfoDTO user;

    public UserPrincipal(String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities,
                         User user) {
        super(username, password, true, true, true, true, authorities);
        this.user = new UserInfoDTO(user.getUsername(), user.getFirstName(), user.getLastName());
    }


    public UserInfoDTO getUser() {
        return user;
    }

    public void setUser(UserInfoDTO user) {
        this.user = user;
    }
}


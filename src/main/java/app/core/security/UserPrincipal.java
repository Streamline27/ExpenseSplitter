package app.core.security;

import app.core.domain.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Vladislav on 2/25/2016.
 */
public class UserPrincipal extends org.springframework.security.core.userdetails.User {
    private User user;

    public UserPrincipal(String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities,
                         User user) {
        super(username, password, true, true, true, true, authorities);
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


package app.security;


import app.core.database.UserDAO;
import app.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladislav on 2/25/2016.
 */

@Service("userDetailsService")
public class CustomizedUserDetailService implements UserDetailsService {
    @Autowired private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.findOne(s);

        List<GrantedAuthority> authorities = buildUserAuthority();
        return buildUserForAuthentication(user, authorities);
    }

    private UserPrincipal buildUserForAuthentication(User user,
                                                     List<GrantedAuthority> authorities) {
        return new UserPrincipal(
                user.getUsername(),
                user.getPassword(),
                authorities,
                user
        );
    }

    private List<GrantedAuthority> buildUserAuthority() {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        result.add(new SimpleGrantedAuthority("LOGINNED_USER"));
        return result;
    }
}

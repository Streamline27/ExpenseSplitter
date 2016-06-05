package app.security;


import app.core.database.UserDAO;
import app.core.domain.UserCredentials;
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
        UserCredentials userCredentials = userDAO.findOne(s);

        List<GrantedAuthority> authorities = buildUserAuthority();
        return buildUserForAuthentication(userCredentials, authorities);
    }

    private UserPrincipal buildUserForAuthentication(UserCredentials userCredentials,
                                                     List<GrantedAuthority> authorities) {
        return new UserPrincipal(
                userCredentials.getUsername(),
                userCredentials.getPassword(),
                authorities,
                userCredentials
        );
    }

    private List<GrantedAuthority> buildUserAuthority() {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        result.add(new SimpleGrantedAuthority("LOGINNED_USER"));
        return result;
    }
}

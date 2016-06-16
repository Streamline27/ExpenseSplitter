package app.config;

import app.security.RestAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Vladislav on 6/3/2016.
 */

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"app.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                    .httpBasic().authenticationEntryPoint(new RestAuthenticationEntryPoint())
                        .and()
                    .authorizeRequests()
                        .antMatchers(HttpMethod.POST,   "/api/user").permitAll()
                        .antMatchers(HttpMethod.POST,   "/api/user/register").permitAll()
                        .antMatchers(HttpMethod.GET,    "/api/user/login").permitAll()
                        .antMatchers(HttpMethod.GET,    "/api/**").authenticated()
                        .antMatchers(HttpMethod.POST,   "/**").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/**").authenticated()
                        .antMatchers(HttpMethod.PUT,    "/**").authenticated()
                    .and()
                        .logout().logoutUrl("/api/user/logout");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}

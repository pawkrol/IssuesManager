package pl.pawkrol.academic.IssuesManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.pawkrol.academic.IssuesManager.shared.entity.CustomUserDetails;
import pl.pawkrol.academic.IssuesManager.user.UserService;

@Configuration
public class AuthenticationManagerConfigurer extends GlobalAuthenticationConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public AuthenticationManagerConfigurer(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username ->
            new CustomUserDetails(userService.findByUsername(username))
        ).passwordEncoder(passwordEncoder);
    }

}

package pl.pawkrol.academic.IssuesManager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder getPasswordEncoider() {
        return new BCryptPasswordEncoder();
    }

    public boolean save(User user) {
        User foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser != null) {
            return false;
        }

        user.setPassword(getPasswordEncoider().encode(user.getPassword()));
        return userRepository.save(user) != null;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
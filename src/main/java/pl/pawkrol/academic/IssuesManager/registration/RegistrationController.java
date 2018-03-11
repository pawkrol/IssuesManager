package pl.pawkrol.academic.IssuesManager.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pawkrol.academic.IssuesManager.shared.entity.role.Role;
import pl.pawkrol.academic.IssuesManager.shared.entity.role.RoleType;
import pl.pawkrol.academic.IssuesManager.user.User;
import pl.pawkrol.academic.IssuesManager.user.UserService;

import java.util.Collections;

@RestController
class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    ResponseEntity register(@RequestBody User user) {
        user.setRoles( Collections.singletonList(new Role(RoleType.USER)));
        if (userService.save(user)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}

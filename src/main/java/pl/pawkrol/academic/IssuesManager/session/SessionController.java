package pl.pawkrol.academic.IssuesManager.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pawkrol.academic.IssuesManager.shared.entity.role.Role;
import pl.pawkrol.academic.IssuesManager.shared.entity.role.RoleType;
import pl.pawkrol.academic.IssuesManager.user.User;
import pl.pawkrol.academic.IssuesManager.user.UserService;

import java.security.Principal;
import java.util.Collections;

@RestController
class SessionController {

    private final UserService userService;
    private final TokenStore tokenStore;

    @Autowired
    public SessionController(UserService userService, @Qualifier("getTokenStore") TokenStore tokenStore) {
        this.userService = userService;
        this.tokenStore = tokenStore;
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

    @GetMapping("/logout")
    ResponseEntity logout(Principal principal) {
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
        OAuth2AccessToken accessToken = tokenStore.getAccessToken(oAuth2Authentication);
        tokenStore.removeAccessToken(accessToken);

        return ResponseEntity.ok().build();
    }
}

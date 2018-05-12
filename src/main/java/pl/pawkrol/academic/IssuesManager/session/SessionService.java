package pl.pawkrol.academic.IssuesManager.session;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pawkrol.academic.IssuesManager.shared.entity.CustomUserDetails;

@Service
public class SessionService {

    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }

}

package pl.pawkrol.academic.IssuesManager.issue;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssueController {

    @GetMapping("/issue")
    public String issue() {
        return "This is an example issue";
    }

}

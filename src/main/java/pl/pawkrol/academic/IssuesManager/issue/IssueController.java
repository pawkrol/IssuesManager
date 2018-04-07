package pl.pawkrol.academic.IssuesManager.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PutMapping("/save")
    Issue save(@RequestBody Issue issue) {
        return issueService.saveIssue(issue);
    }

    @GetMapping("/all")
    List<Issue> getAll() {
        return issueService.getAll();
    }
}

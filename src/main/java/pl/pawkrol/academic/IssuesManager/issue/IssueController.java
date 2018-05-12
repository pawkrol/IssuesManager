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

    @PutMapping("/save/{projectId}")
    Issue save(@RequestBody Issue issue, @PathVariable String projectId) {
        issue.setProjectId(projectId);
        return issueService.saveIssue(issue);
    }

    @GetMapping("/all/{projectId}")
    List<Issue> getAll(@PathVariable String projectId) {
        return issueService.getAll(projectId);
    }
}

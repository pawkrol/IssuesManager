package pl.pawkrol.academic.IssuesManager.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pawkrol.academic.IssuesManager.project.Project;
import pl.pawkrol.academic.IssuesManager.project.ProjectService;
import pl.pawkrol.academic.IssuesManager.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {

    private final IssueService issueService;
    private final UserService userService;
    private final ProjectService projectService;

    @Autowired
    public IssueController(IssueService issueService, UserService userService, ProjectService projectService) {
        this.issueService = issueService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @PutMapping("/save/{projectId}")
    Issue save(@RequestBody Issue issue, @PathVariable String projectId) {
        if (issue.getId() == null) {
            issue.setProjectId(projectId);
            issue.setTimeReported(System.currentTimeMillis());
            issue.setReporterUserId(userService.getCurrentUser().getId());
        }
        return issueService.saveIssue(issue);
    }

    @PostMapping("/update")
    Issue update(@RequestBody Issue issue) {
        return issueService.saveIssue(issue);
    }

    @DeleteMapping("/delete/{issueId}")
    ResponseEntity delete(@PathVariable String issueId) {
        Issue issue = issueService.getById(issueId);
        Project project = projectService.getById(issue.getProjectId());

        if (!userService.getCurrentUser().getId().equals(project.getOwnerId())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        issueService.deleteIssue(issueId);
        return ResponseEntity
                .ok()
                .build();
    }

    @GetMapping("/get/{issueId}")
    Issue getById(@PathVariable String issueId) {
        return issueService.getById(issueId);
    }

    @GetMapping("/all/{projectId}")
    List<Issue> getAll(@PathVariable String projectId) {
        return issueService.getAll(projectId);
    }
}

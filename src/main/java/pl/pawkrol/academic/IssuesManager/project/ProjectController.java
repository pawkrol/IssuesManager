package pl.pawkrol.academic.IssuesManager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pawkrol.academic.IssuesManager.session.SessionService;
import pl.pawkrol.academic.IssuesManager.user.User;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;
    private SessionService sessionService;

    @Autowired
    public ProjectController(ProjectService projectService, SessionService sessionService) {
        this.projectService = projectService;
        this.sessionService = sessionService;
    }

    @PutMapping("/save")
    Project save(@RequestBody Project project) {
        return projectService.saveProject(project);
    }

    @GetMapping("/get/{projectId}")
    Project getById(@PathVariable String projectId) {
        return projectService.getById(projectId);
    }

    @GetMapping("/all")
    List<Project> all() {
        return projectService.getAll();
    }

    @DeleteMapping("/remove")
    ResponseEntity remove(@RequestParam String id) {
        Project project = projectService.getById(id);
        if (!project.getOwnerId().equals(sessionService.getUserId())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        projectService.remove(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/add-user/{projectId}")
    ResponseEntity addUserToProject(@PathVariable String projectId,
                                    @PathParam(value = "username") String username) {
        if (projectService.addUserToProject(projectId, username)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users/{projectId}")
    List<User> getProjectUsers(@PathVariable String projectId) {
        return projectService.getProjectUsers(projectId);
    }
}

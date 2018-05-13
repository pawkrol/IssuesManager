package pl.pawkrol.academic.IssuesManager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
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
        projectService.remove(id);
        return ResponseEntity.ok().build();
    }
}

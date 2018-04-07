package pl.pawkrol.academic.IssuesManager.project;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/all")
    List<Project> all() {
        return projectService.getAll();
    }
}

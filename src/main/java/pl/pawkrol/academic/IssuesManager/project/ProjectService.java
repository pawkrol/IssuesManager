package pl.pawkrol.academic.IssuesManager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.pawkrol.academic.IssuesManager.session.SessionService;
import pl.pawkrol.academic.IssuesManager.user.User;
import pl.pawkrol.academic.IssuesManager.user.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private MongoTemplate mongoTemplate;
    private ProjectRepository projectRepository;
    private SessionService sessionService;
    private UserService userService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, MongoTemplate mongoTemplate,
                          SessionService sessionService, UserService userService) {
        this.projectRepository = projectRepository;
        this.mongoTemplate = mongoTemplate;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    public Project saveProject(Project project) {
        if (project.getId() == null) {
            String userId = sessionService.getUserId();
            project.setUserIds(Arrays.asList(userId));
            project.setOwnerId(userId);
        }
        return projectRepository.save(project);
    }

    public Project getById(String id) {
        return projectRepository.findOne(id);
    }

    public List<Project> getAll() {
        Query query = new Query();
        query.addCriteria(Criteria.where("userIds").all(sessionService.getUserId()));

        return mongoTemplate.find(query, Project.class);
    }

    public void remove(String id) {
        projectRepository.delete(id);
    }

    public boolean addUserToProject(String projectId, String username) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return false;
        }

        Project project = projectRepository.findOne(projectId);
        project.getUserIds().add(user.getId());
        projectRepository.save(project);

        return true;
    }

    public List<User> getProjectUsers(String projectId) {
        Project project = projectRepository.findOne(projectId);
        if (project == null) return null;

        List<String> userIds = project.getUserIds();
        return userIds
                .stream()
                .map(userId -> userService.findById(userId))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

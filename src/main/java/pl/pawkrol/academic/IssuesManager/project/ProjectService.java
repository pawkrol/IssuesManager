package pl.pawkrol.academic.IssuesManager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pawkrol.academic.IssuesManager.shared.entity.CustomUserDetails;
import pl.pawkrol.academic.IssuesManager.user.User;
import pl.pawkrol.academic.IssuesManager.user.UserService;

import java.security.Principal;
import java.util.List;

@Service
public class ProjectService {

    private MongoTemplate mongoTemplate;
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, MongoTemplate mongoTemplate) {
        this.projectRepository = projectRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public Project saveProject(Project project) {
        project.setUserId(getUserId());
        return projectRepository.save(project);
    }

    public List<Project> getAll() {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(getUserId()));

        return mongoTemplate.find(query, Project.class);
    }

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails.getId();
    }
}

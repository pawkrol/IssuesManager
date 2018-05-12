package pl.pawkrol.academic.IssuesManager.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.pawkrol.academic.IssuesManager.session.SessionService;
import pl.pawkrol.academic.IssuesManager.shared.entity.CustomUserDetails;

import java.util.List;

@Service
public class ProjectService {

    private MongoTemplate mongoTemplate;
    private ProjectRepository projectRepository;
    private SessionService sessionService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, MongoTemplate mongoTemplate,
                          SessionService sessionService) {
        this.projectRepository = projectRepository;
        this.mongoTemplate = mongoTemplate;
        this.sessionService = sessionService;
    }

    public Project saveProject(Project project) {
        project.setUserId(sessionService.getUserId());
        return projectRepository.save(project);
    }

    public List<Project> getAll() {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(sessionService.getUserId()));

        return mongoTemplate.find(query, Project.class);
    }

    public void remove(String id) {
        projectRepository.delete(id);
    }

}

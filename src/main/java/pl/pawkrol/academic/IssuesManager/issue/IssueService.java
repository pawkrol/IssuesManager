package pl.pawkrol.academic.IssuesManager.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import pl.pawkrol.academic.IssuesManager.session.SessionService;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private MongoTemplate mongoTemplate;
    private SessionService sessionService;

    @Autowired
    public IssueService(IssueRepository issueRepository, MongoTemplate mongoTemplate,
                        SessionService sessionService) {
        this.issueRepository = issueRepository;
        this.mongoTemplate = mongoTemplate;
        this.sessionService = sessionService;
    }

    Issue saveIssue(Issue issue) {
        if (issue.getId() == null) {
            issue.setReporterUserId(sessionService.getUserId());
            issue.setState(Issue.State.TODO);
        }

        return issueRepository.save(issue);
    }

    Issue getById(String id) {
        return issueRepository.findOne(id);
    }

    List<Issue> getAll(String projectId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("projectId").is(projectId));

        return mongoTemplate.find(query, Issue.class);
    }
}

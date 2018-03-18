package pl.pawkrol.academic.IssuesManager.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    Issue saveIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    Issue getById(String id) {
        return issueRepository.findOne(id);
    }

    List<Issue> getAll() {
        return issueRepository.findAll();
    }
}

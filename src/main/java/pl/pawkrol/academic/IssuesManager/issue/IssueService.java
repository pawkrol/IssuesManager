package pl.pawkrol.academic.IssuesManager.issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    Issue saveIssue(Issue issue) {
        return issueRepository.save(issue);
    }

    List<Issue> getAll() {
        return issueRepository.findAll();
    }
}

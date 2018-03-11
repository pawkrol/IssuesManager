package pl.pawkrol.academic.IssuesManager.issue;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IssueRepository extends MongoRepository<Issue, String> {}

package pl.pawkrol.academic.IssuesManager.user;

import org.springframework.data.mongodb.repository.MongoRepository;

interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    User findById(String id);

}

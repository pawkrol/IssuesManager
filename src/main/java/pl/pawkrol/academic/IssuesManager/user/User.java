package pl.pawkrol.academic.IssuesManager.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.pawkrol.academic.IssuesManager.shared.entity.role.Role;

import java.util.List;

@Data
@Document
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @NotEmpty
    @Indexed(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    private List<Role> roles;

    public User(String username, String password, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}

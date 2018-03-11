package pl.pawkrol.academic.IssuesManager.shared.entity.role;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Role {

    @Id
    private String id;

    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role(RoleType roleType) {
        this.name = roleType.getName();
    }

}

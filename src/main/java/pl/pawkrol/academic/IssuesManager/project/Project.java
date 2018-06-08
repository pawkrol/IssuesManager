package pl.pawkrol.academic.IssuesManager.project;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class Project {

    @Id
    private String id;

    @NotNull
    private List<String> userIds;

    @NotNull
    private String ownerId;

    @NotNull
    private String title;

    private String description;

}

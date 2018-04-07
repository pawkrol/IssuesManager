package pl.pawkrol.academic.IssuesManager.project;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
public class Project {

    @Id
    private String id;

    @NotNull
    private String userId;

    @NotNull
    private String title;

    private String description;

}

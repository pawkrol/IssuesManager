package pl.pawkrol.academic.IssuesManager.issue;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
public class Issue {

    @Id
    private String id;

    @NotNull
    private String title;

}

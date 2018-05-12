package pl.pawkrol.academic.IssuesManager.issue;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
class Issue {

    private enum Priority { HIGH, MEDIUM, LOW }

    @Id
    private String id;

    @NotNull
    private String projectId;

    @NotNull
    private String title;

    private long timeReported;

    private String description;

    private Priority priority;

    @NotNull
    private String reporterUserId;

    private String assigneeUserId;
}

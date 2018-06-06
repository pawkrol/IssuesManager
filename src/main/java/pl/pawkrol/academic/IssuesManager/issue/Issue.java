package pl.pawkrol.academic.IssuesManager.issue;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;

@Data
class Issue {

    public enum Priority { HIGH, MEDIUM, LOW }

    public enum State { TODO, IN_PROGRESS, DONE }

    @Id
    private String id;

    @NotNull
    private String projectId;

    @NotNull
    private String title;

    private long timeReported;

    private String description;

    private Priority priority;

    private State state;

    @NotNull
    private String reporterUserId;

    private String assigneeUserId;
}

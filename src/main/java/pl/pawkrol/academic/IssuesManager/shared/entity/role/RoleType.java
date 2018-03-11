package pl.pawkrol.academic.IssuesManager.shared.entity.role;

public enum RoleType {

    USER("ROLE_USER");

    private String name;

    RoleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

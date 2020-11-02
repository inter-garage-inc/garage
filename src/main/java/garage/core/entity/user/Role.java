package garage.core.entity.user;

public enum Role {
    ADMIN("admin"),
    MANAGER("manager"),
    OPERATOR("operator");

    private String value;

    Role(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

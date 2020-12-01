package garage.core.entity.user;

public enum Status {
    ACTIVE("ativo"),
    DISABLE("inativo");

    private String value;

    Status(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

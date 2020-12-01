package garage.core.entity;

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

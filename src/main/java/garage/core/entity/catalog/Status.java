package garage.core.entity.catalog;

public enum Status {
    AVAILABLE("available"),
    UNAVAILABLE("unavailable");

    private final String value;

    Status(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package garage.core.entity.order;

public enum Status {
    OPEN("open"),
    PAID("paid"),
    CANCELED("canceled");

    private String value;

    Status(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

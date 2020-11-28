package garage.core.entity.plan;

public enum Type {
    MONTHLY("Mensal"),
    BIWEEKLY("Quinzenal");

    private final String value;

    Type(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

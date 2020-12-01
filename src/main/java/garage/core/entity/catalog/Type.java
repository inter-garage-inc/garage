package garage.core.entity.catalog;

public enum Type {
    PARKING("parking"),
    OTHER("other");

    private final String value;

    Type(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

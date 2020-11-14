package garage.core.entity.parking;

public enum SpaceStatus {
    OCCUPIED("AVAILABLE"),
    VACANT("UNAVAILABLE"),
    RESERVED("RESERVED");

    private final String value;

    SpaceStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
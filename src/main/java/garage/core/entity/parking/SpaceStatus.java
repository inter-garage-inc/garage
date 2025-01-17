package garage.core.entity.parking;

public enum SpaceStatus {
    OCCUPIED("Ocupada"),
    VACANT("Livre"),
    DISABLED("Desativada");

    private final String value;

    SpaceStatus(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
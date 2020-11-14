package garage.core.entity.order;

public enum PaymentMethod {
    CASH("cash"),
    CARD("card");

    private final String value;

    PaymentMethod(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

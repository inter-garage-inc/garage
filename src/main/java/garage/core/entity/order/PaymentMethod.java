package garage.core.entity.order;

public enum PaymentMethod {
    CASH("cash"),
    CARD("card");

    private String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

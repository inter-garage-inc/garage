package factories;

import garage.core.entity.Customer;

public class CustomerFactory {
    public static Customer customer() {
        return Customer
                .builder()
                .name("John Doe")
                .build();
    }

    public static Customer invalidCustomer() {
        return Customer
                .builder()
                .name(null)
                .build();
    }
}

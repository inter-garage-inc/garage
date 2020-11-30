package factories;

import garage.core.entity.Customer;

public class CustomerFactory {
    public static Customer customer() {
        return Customer
                .builder()
                .name("John Doe")
                .cpfCnpj("00000000000")
                .phone("5511999999999")
                .address(AddressFactory.address())
                .build();
    }

    public static Customer invalidCustomer() {
        return Customer
                .builder()
                .build();
    }
}

package factories;

import garage.core.entity.Address;
import garage.core.entity.address.State;

public class AddressFactory {
    public static Address address() {
        return Address
                .builder()
                .street("Street one")
                .number("12343A")
                .complement("a complement")
                .neighborhood("District nine")
                .postalCode(12321)
                .country("Brasil")
                .state(State.SP)
                .build();
    }
    public static Address invalidAddress() {
        return Address
                .builder()
                .build();
    }
}

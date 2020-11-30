package factories;

import garage.core.entity.Address;
import garage.core.entity.address.Country;
import garage.core.entity.address.State;

public class AddressFactory {
    public static Address address() {
        return Address
                .builder()
                .street("A Street")
                .number("1234")
                .complement("A complement")
                .postalCode("00001000")
                .neighborhood("A District")
                .city("A City")
                .state(State.SP)
                .country(Country.BRAZIL)
                .build();
    }
    
    public static Address invalidAddress() {
        return Address
                .builder()
                .build();
    }
}

package factories;

import garage.core.entity.Parking;

public class ParkingFactory {
    public static Parking parking() {
        return Parking
                .builder()
                .build();
    }

    public static Parking invalidParking() {
        return Parking
                .builder()
                .build();
    }
}

package factories;

import garage.core.entity.parking.ParkingSpace;

public class ParkingSpaceFactory {
    public static ParkingSpace parkingSpace() {
        return ParkingSpace
                .builder()
                .build();
    }

    public static ParkingSpace invalidParkingSpace() {
        return ParkingSpace
                .builder()
                .build();
    }
}

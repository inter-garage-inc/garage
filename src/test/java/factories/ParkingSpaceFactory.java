package factories;

import garage.core.entity.parking.ParkingSpace;

public class ParkingSpaceFactory {
    public static ParkingSpace parkingSpace() {
        return ParkingSpace
                .builder()
                .number(123456)
                .build();
    }

    public static ParkingSpace invalidParkingSpace() {
        return ParkingSpace
                .builder()
                .build();
    }
}

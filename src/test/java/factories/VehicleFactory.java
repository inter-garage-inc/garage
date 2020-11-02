package factories;

import garage.core.entity.Vehicle;

public class VehicleFactory {
    public static Vehicle vehicle() {
        return Vehicle
                .builder()
                .licencePlate("XPTO-12324")
                .build();
    }

    public static Vehicle invalidVehicle() {
        return Vehicle
                .builder()
                .build();
    }
}

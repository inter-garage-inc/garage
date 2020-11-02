package garage.core.entity;

import factories.OrderFactory;
import factories.ParkingFactory;
import factories.VehicleFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ParkingTest extends JUnitSupport {
    @Test
    public void whenAVehicleIsValid(){
        var entity = VehicleFactory.vehicle();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isEmpty();
    }

    @Test
    public void whenAVehicleInvalidValid(){
        var entity = VehicleFactory.invalidVehicle();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isNotEmpty();
    }
}
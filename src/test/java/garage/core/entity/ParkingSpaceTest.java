package garage.core.entity;

import factories.ParkingSpaceFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ParkingSpaceTest extends JUnitSupport {
    @Test
    public void whenAParkingSpaceIsValid(){
        var entity = ParkingSpaceFactory.parkingSpace();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isEmpty();
    }

    @Test
    public void whenAParkingSpaceInvalidValid(){
        var entity = ParkingSpaceFactory.invalidParkingSpace();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isNotEmpty();
    }
}
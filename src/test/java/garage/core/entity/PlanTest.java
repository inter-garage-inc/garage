package garage.core.entity;

import factories.ParkingFactory;
import factories.PlanFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PlanTest extends JUnitSupport {
    @Test
    public void whenAPlanIsValid(){
        var entity = PlanFactory.plan();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isEmpty();
    }

    @Test
    public void whenAPlanInvalidValid(){
        var entity = PlanFactory.invalidPlan();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isNotEmpty();
    }
}
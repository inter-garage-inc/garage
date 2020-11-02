package garage.core.entity;

import factories.CustomerFactory;
import factories.OrderFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OrderTest extends JUnitSupport {
    @Test
    public void whenAOrderIsValid(){
        var entity = OrderFactory.order();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isEmpty();
    }

    @Test
    public void whenAOrderInvalidValid(){
        var entity = OrderFactory.invalidOrder();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isNotEmpty();
    }
}
package garage.core.entity.order;

import factories.OrderFactory;
import factories.order.ItemFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ItemTest extends JUnitSupport {
    @Test
    public void whenAItemIsValid(){
        var entity = ItemFactory.item();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isEmpty();
    }

    @Test
    public void whenAItemInvalidValid(){
        var entity = ItemFactory.invalidItem();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isNotEmpty();
    }
}
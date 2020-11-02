package garage.core.entity;

import factories.CustomerFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CustomerTest extends JUnitSupport {
    @Test
    public void whenACustomerIsValid(){
        var entity = CustomerFactory.customer();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isEmpty();
    }

    @Test
    public void whenACustomerInvalidValid(){
        var entity = CustomerFactory.invalidCustomer();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isNotEmpty();
    }
}
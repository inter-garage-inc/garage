package garage.core.entity;

import factories.AddressFactory;
import factories.CustomerFactory;
import garage.core.repository.AddressRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

import javax.validation.Validation;
import javax.validation.Validator;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AddressTest extends JUnitSupport {
    @Test
    public void whenAAddressIsValid(){
        var entity = AddressFactory.address();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isEmpty();
    }

    @Test
    public void whenAAddressInvalidValid() {
        var entity= AddressFactory.invalidAddress();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isNotEmpty();
    }
}
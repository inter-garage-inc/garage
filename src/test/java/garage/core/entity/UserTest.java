package garage.core.entity;

import factories.UserFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserTest extends JUnitSupport<User> {
    @Test
    public void whenAUserIsValid() {
        var entity = UserFactory.user();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isEmpty();
    }

    @Test
    public void whenAUserInvalidValid() {
        var entity = UserFactory.invalidUser();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isNotEmpty();
    }
}
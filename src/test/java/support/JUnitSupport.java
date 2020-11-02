package support;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration
@TestPropertySource("/test.properties")
@ExtendWith(SpringExtension.class)
public abstract class JUnitSupport<T> {
    public Set<ConstraintViolation<T>> isValid(T entity){
        return Validation
                .buildDefaultValidatorFactory()
                .getValidator()
                .validate(entity);
    }
}

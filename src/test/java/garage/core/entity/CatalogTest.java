package garage.core.entity;

import factories.CatalogFactory;
import factories.CustomerFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CatalogTest extends JUnitSupport {
    @Test
    public void whenACatalogIsValid(){
        var entity = CatalogFactory.catalog();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isEmpty();
    }

    @Test
    public void whenACatalogInvalidValid(){
        var entity = CatalogFactory.invalidCatalog();
        var validations = isValid(entity);
        Assertions.assertThat(validations).isNotEmpty();
    }
}
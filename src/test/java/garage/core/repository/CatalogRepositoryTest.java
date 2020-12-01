package garage.core.repository;

import factories.CatalogFactory;
import factories.PlanFactory;
import garage.core.entity.Catalog;
import garage.core.repository.CatalogRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class CatalogRepositoryTest extends JUnitSupport {

    @Autowired
    private CatalogRepository repository;

    @Autowired
    private PlanRepository planRepository;

    private Catalog catalog;

    @BeforeEach
    public void setUp() {
        this.catalog = CatalogFactory.catalog();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void whenCreatedACatalog() {
        var expected = repository.save(this.catalog);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
    }

    @Test
    public void whenCatalogBelongsToPlan() {
        var catalog = repository.save(this.catalog);

        var plan = PlanFactory.plan();
        plan.setCatalog(List.of(catalog));
        planRepository.save(plan);

        var expected = repository.findById(catalog.getId()).get();

        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
    }


/*
    @Test
    public void whenFindCatalogById() {
        var catalog = repository.save(this.catalog);
        var expected = repository.findById(catalog.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenFindAllCatalog() {
        var catalog = repository.save(this.catalog);
        var expected = repository.findAll();
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenDeleteACatalog() {
        var catalog = repository.save(this.catalog);
        repository.findById(catalog.getId()).map(c -> {
            repository.deleteById(c.getId());
            return null;
        });

        var expected = repository.findById(catalog.getId()).orElse(null);
        assertThat(expected).isNull();
    }
 */
}


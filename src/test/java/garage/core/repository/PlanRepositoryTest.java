package garage.core.repository;

import factories.CustomerFactory;
import factories.PlanFactory;
import garage.core.entity.Plan;
import garage.core.entity.Status;
import garage.core.repository.PlanRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PlanRepositoryTest extends JUnitSupport {

    @Autowired
    private PlanRepository repository;

    private Plan plan;

    @BeforeEach
    public void setUp() {
        this.plan = PlanFactory.plan();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void whenCreatedAPlan() {
        var expected = repository.save(this.plan);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
        assertThat(expected.getStatus()).isEqualTo(Status.ACTIVE);
        assertThat(expected.getDescription()).isEqualTo("some description");
        assertThat(expected.getPrice()).isEqualTo(BigDecimal.valueOf(10.00));
    }

    @Test
    public void whenFindIPlanById() {
        var plan = repository.save(this.plan);
        var expected = repository.findById(plan.getId()).get();
        assertThat(expected).isNotNull();
        assertThat(expected.getStatus().getValue()).isEqualTo("active");
    }

    @Test
    public void whenFindAllIPlan() {
        var item = repository.save(this.plan);
        var expected = repository.findAll();
        assertThat(expected).isNotNull();
        assertThat(expected.size()).isEqualTo(1);
    }

    @Test
    public void whenDeleteAPlan() {
        var plan = repository.save(this.plan);
        repository.findById(plan.getId()).map(p -> {
            repository.deleteById(p.getId());
            return null;
        });

        var expected = repository.findById(plan.getId()).orElse(null);
        assertThat(expected).isNull();
    }
}
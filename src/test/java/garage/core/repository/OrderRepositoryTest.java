package garage.core.repository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.*;
import factories.CustomerFactory;
import factories.OrderFactory;
import garage.core.entity.Order;
import garage.core.entity.order.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;
import javax.validation.ConstraintViolationException;
import java.util.Optional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OrderRepositoryTest extends JUnitSupport {

    @Autowired
    private OrderRepository repository;

    private Order order;

    @BeforeEach
    public void setUp() {
        this.order = OrderFactory.order();
        this.order.setCustomer(CustomerFactory.customer());
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void whenCreatedAOrder() {
        var expected = repository.save(this.order);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
        assertThat(expected.getLicensePlate()).isEqualTo("XXX-0000");
        assertThat(expected.getStatus()).isEqualTo(Status.OPEN);
        assertThat(expected.getCustomer()).isNotNull();
    }

    @Test
    public void whenCreatedAOrderInvalid() {
        assertThrows(ConstraintViolationException.class, () -> repository.save(OrderFactory.invalidOrder()));
    }

    @Test
    public void whenFindOrderById() {
        var order = repository.save(this.order);
        var expected = repository.findById(order.getId()).get();
        assertThat(expected).isNotNull();
        assertThat(expected.getLicensePlate()).isEqualTo("XXX-0000");
        assertThat(expected.getStatus()).isEqualTo(Status.OPEN);
        assertThat(expected.getCustomer()).isNotNull();
    }

    @Test
    public void whenFindOrderByIdAndReturnNull() {
        var expected = repository.findById(5L);
        assertThat(expected).isEqualTo(Optional.empty());
    }

    @Test
    public void whenFindAllOrder() {
        var order = repository.save(this.order);
        var expected = repository.findAll();
    }

    @Test
    public void whenFindAllOrderAndReturnNull() {
        var expected = repository.findAll();
        assertThat(expected.size()).isEqualTo(0);
    }

    @Test
    public void whenDeleteAOrder() {
        var order = repository.save(this.order);
        repository.findById(order.getId()).map(o -> {
            repository.deleteById(o.getId());
            return null;
        });

        var expected = repository.findById(order.getId()).orElse(null);
        assertThat(expected).isNull();
    }
}


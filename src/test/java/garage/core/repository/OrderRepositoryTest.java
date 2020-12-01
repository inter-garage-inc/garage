package garage.core.repository;

import factories.CustomerFactory;
import factories.OrderFactory;
import factories.order.ItemFactory;
import garage.core.entity.Order;
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
public class OrderRepositoryTest extends JUnitSupport {
/*
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
    }

    @Test
    public void whenFindOrderById() {
        var order = repository.save(this.order);
        var expected = repository.findById(order.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenFindAllOrder() {
        var order = repository.save(this.order);
        var expected = repository.findAll();
        assertThat(expected).isNotNull();
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

    @Test
    public void whenAOrderHasItems() {
        var items = List.of(ItemFactory.item());
        this.order.setItems(items);
        var expected = repository.save(this.order);
        assertThat(expected).isNotNull();
        assertThat(expected.getItems().size()).isEqualTo(1);
    }
 */
}


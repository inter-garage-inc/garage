package garage.core.repository;

import factories.OrderFactory;
import factories.order.ItemFactory;
import garage.core.entity.order.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import support.JUnitSupport;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ItemRepositoryTest extends JUnitSupport {

    @Autowired
    private OrderItemRepository repository;

    private Item item;

    @BeforeEach
    public void setUp() {
        this.item = ItemFactory.item();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void whenCreatedAItem() {
        var expected = repository.save(this.item);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
    }

    @Test
    public void whenFindItemById() {
        var item = repository.save(this.item);
        var expected = repository.findById(item.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenFindAllItem() {
        var item = repository.save(this.item);
        var expected = repository.findAll();
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenDeleteAItem() {
        var item = repository.save(this.item);
        repository.findById(item.getId()).map(o -> {
            repository.deleteById(o.getId());
            return null;
        });

        var expected = repository.findById(item.getId()).orElse(null);
        assertThat(expected).isNull();
    }

    @Test
    public void whenAItemHasOrder() {
        this.item.setOrder(OrderFactory.order());
        var expected = repository.save(this.item);
        assertThat(expected).isNotNull();
        assertThat(expected.getOrder()).isNotNull();
    }
}
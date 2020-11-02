package garage.core.repository;

import factories.*;
import garage.core.entity.Customer;
import garage.core.repository.CustomerRepository;
import garage.core.repository.PlanRepository;
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
public class CustomerRepositoryTest extends JUnitSupport {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private PlanRepository planRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        this.customer = CustomerFactory.customer();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void whenCreatedACustomer() {
        var expected = repository.save(this.customer);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
    }

    @Test
    public void whenFindCustomerById() {
        var customer = repository.save(this.customer);
        var expected = repository.findById(customer.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenFindAllCustomer() {
        var customer = repository.save(this.customer);
        var expected = repository.findAll();
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenDeleteACustomer() {
        var customer = repository.save(this.customer);
        repository.findById(customer.getId()).map(c -> {
            repository.deleteById(c.getId());
            return null;
        });

        var expected = repository.findById(customer.getId()).orElse(null);
        assertThat(expected).isNull();
    }

    @Test
    public void whenACustomerHasOrders() {
        var orders = List.of(OrderFactory.order());
        customer.setOrders(orders);

        var expected = repository.save(this.customer);
        assertThat(expected).isNotNull();
        assertThat(expected.getOrders().size()).isEqualTo(1);
    }

    @Test
    public void whenACustomerHasVehicles() {
        var vehicles = List.of(VehicleFactory.vehicle());
        customer.setVehicles(vehicles);

        var expected = repository.save(this.customer);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
        assertThat(expected.getVehicles().size()).isEqualTo(1);
    }

    @Test
    public void whenACustomerHasPlan() {
        var plan = planRepository.save(PlanFactory.plan());
        var customer = repository.save(this.customer);
        customer.setPlan(plan);

        var expected = repository.save(customer);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
        assertThat(expected.getPlan()).isNotNull();
        assertThat(expected.getPlan().getId()).isEqualTo(1);
    }

    @Test
    public void whenACustomerHasAddresses() {
        var address = AddressFactory.address();
        this.customer.setAddresses(List.of(address));
        var expected = repository.save(this.customer);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
        assertThat(expected.getAddresses()).isNotNull();
        assertThat(expected.getAddresses().size()).isEqualTo(1);
    }
}


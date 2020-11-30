package garage.core.repository;

import factories.CustomerFactory;
import factories.VehicleFactory;
import garage.core.entity.Vehicle;
import garage.core.repository.VehicleRepository;
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
public class VehicleRepositoryTest extends JUnitSupport {
/*
    @Autowired
    private VehicleRepository repository;

    private Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        this.vehicle = VehicleFactory.vehicle();
        this.vehicle.setCustomer(CustomerFactory.customer());
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void whenCreatedAVehicle() {
        var expected = repository.save(this.vehicle);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
    }

    @Test
    public void whenFindVehicleById() {
        var vehicle = repository.save(this.vehicle);
        var expected = repository.findById(vehicle.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenFindAllVehicle() {
        var vehicle = repository.save(this.vehicle);
        var expected = repository.findAll();
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenDeleteAVehicle() {
        var vehicle = repository.save(this.vehicle);
        repository.findById(vehicle.getId()).map(o -> {
            repository.deleteById(o.getId());
            return null;
        });

        var expected = repository.findById(vehicle.getId()).orElse(null);
        assertThat(expected).isNull();
    }
 */
}
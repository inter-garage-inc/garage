package garage.core.repository;

import factories.ParkingFactory;
import factories.ParkingSpaceFactory;
import garage.core.entity.Parking;
import garage.core.repository.ParkingRepository;
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
public class ParkingRepositoryTest extends JUnitSupport {

    @Autowired
    private ParkingRepository repository;

    private Parking parking;

    @BeforeEach
    public void setUp() {
        this.parking = ParkingFactory.parking();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void whenCreatedAParking() {
        var expected = repository.save(this.parking);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
    }

    @Test
    public void whenFindIParkingById() {
        var plan = repository.save(this.parking);
        var expected = repository.findById(plan.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenFindAllParking() {
        var item = repository.save(this.parking);
        var expected = repository.findAll();
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenDeleteAParking() {
        var plan = repository.save(this.parking);
        repository.findById(plan.getId()).map(p -> {
            repository.deleteById(p.getId());
            return null;
        });

        var expected = repository.findById(parking.getId()).orElse(null);
        assertThat(expected).isNull();
    }

    @Test
    public void whenAParkingHasParkingSpace() {
        var parkingSpace = ParkingSpaceFactory.parkingSpace();
        this.parking.setParkingSpace(parkingSpace);

        var expected = repository.save(this.parking);
        assertThat(expected).isNotNull();
        assertThat(expected.getParkingSpace()).isNotNull();
    }
}
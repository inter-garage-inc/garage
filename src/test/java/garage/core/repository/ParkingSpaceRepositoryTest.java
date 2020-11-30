package garage.core.repository;

import factories.ParkingSpaceFactory;
import garage.core.entity.parking.ParkingSpace;
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
public class ParkingSpaceRepositoryTest extends JUnitSupport {
/*
    @Autowired
    private ParkingSpaceRepository repository;

    private ParkingSpace parkingSpace;

    @BeforeEach
    public void setUp() {
        this.parkingSpace = ParkingSpaceFactory.parkingSpace();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void whenCreatedAParking() {
        var expected = repository.save(this.parkingSpace);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
    }

    @Test
    public void whenFindParkingById() {
        var plan = repository.save(this.parkingSpace);
        var expected = repository.findById(plan.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenFindAllParking() {
        var item = repository.save(this.parkingSpace);
        var expected = repository.findAll();
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenDeleteAParking() {
        var plan = repository.save(this.parkingSpace);
        repository.findById(plan.getId()).map(p -> {
            repository.deleteById(p.getId());
            return null;
        });

        var expected = repository.findById(parkingSpace.getId()).orElse(null);
        assertThat(expected).isNull();
    }
 */
}
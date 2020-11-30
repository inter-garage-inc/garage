package garage.core.repository;

import factories.AddressFactory;
import factories.CustomerFactory;
import garage.core.entity.Address;
import garage.core.repository.AddressRepository;
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
public class AddressRepositoryTest extends JUnitSupport {

    @Autowired
    private AddressRepository repository;

    private Address address;

    @BeforeEach
    public void setUp() {
        this.address = AddressFactory.address();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void whenCreatedAAddress() {
        var expected = repository.save(this.address);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
    }

    @Test
    public void whenFindAddressById() {
        var address = repository.save(this.address);
        var expected = repository.findById(address.getId());
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenFindAllAddress() {
        var address = repository.save(this.address);
        var expected = repository.findAll();
        assertThat(expected).isNotNull();
    }

    @Test
    public void whenDeleteAAddress() {
        var address = repository.save(this.address);
        repository.findById(address.getId()).map(a -> {
            repository.deleteById(a.getId());
            return null;
        });

        var expected = repository.findById(address.getId()).orElse(null);
        assertThat(expected).isNull();
    }
}
package garage.core.repository;

import factories.UserFactory;
import garage.core.entity.User;
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
public class UserRepositoryTest extends JUnitSupport {
/*
    @Autowired
    private UserRepository repository;

    private User user;

    @BeforeEach
    public void setUp() {
        this.user = UserFactory.user();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void whenCreatedAUser() {
        var expected = repository.save(user);
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
        assertThat(expected.getName()).isEqualTo(user.getName());
        assertThat(expected.getUsername()).isEqualTo(user.getUsername());
        assertThat(expected.getPassword()).isEqualTo(user.getPassword());
        assertThat(expected.getRole()).isEqualTo(user.getRole());
        assertThat(expected.getStatus()).isEqualTo(user.getStatus());
    }

    @Test
    public void whenFindByUsername() {
        repository.save(this.user);
        var expected = repository.findByUsername(user.getUsername()).get();
        assertThat(expected).isNotNull();
        assertThat(expected.getId()).isNotNull();
        assertThat(expected.getName()).isEqualTo(user.getName());
        assertThat(expected.getUsername()).isEqualTo(user.getUsername());
        assertThat(expected.getPassword()).isEqualTo(user.getPassword());
        assertThat(expected.getRole()).isEqualTo(user.getRole());
        assertThat(expected.getStatus()).isEqualTo(user.getStatus());
    }
 */
}
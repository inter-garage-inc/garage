package garage.core.repository;

import garage.core.entity.User;
import garage.core.entity.user.Role;
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

    @Autowired
    private UserRepository repository;

    private User user;

    @BeforeEach
    public void setUp() {
        this.user = User.builder().username("foo").password("$2y$12$fPqQBe.GDMBsuruFAYNApOJ3nIJ6k8WSI4urNcAK8lFJMuwNLNA1u").role(Role.ADMIN).build();
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
        assertThat(expected.getUsername()).isEqualTo("foo");
        assertThat(expected.getPassword()).isEqualTo("$2y$12$fPqQBe.GDMBsuruFAYNApOJ3nIJ6k8WSI4urNcAK8lFJMuwNLNA1u");
        assertThat(expected.getRole().getValue()).isEqualTo("admin");
    }

    @Test
    public void whenFindByUsername() {
        repository.save(this.user);
        var expected = repository.findByUsername("foo").get();
        assertThat(expected).isNotNull();
        assertThat(expected.getUsername()).isEqualTo("foo");
        assertThat(expected.getPassword()).isEqualTo("$2y$12$fPqQBe.GDMBsuruFAYNApOJ3nIJ6k8WSI4urNcAK8lFJMuwNLNA1u");
        assertThat(expected.getRole().getValue()).isEqualTo("admin");
    }
}
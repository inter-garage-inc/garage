package factories;

import garage.core.entity.Status;
import garage.core.entity.User;
import garage.core.entity.user.Role;

public class UserFactory {
    public static User user() {
        return User
                .builder()
                .name("So-and-so")
                .username("foo")
                .password("$2y$12$fPqQBe.GDMBsuruFAYNApOJ3nIJ6k8WSI4urNcAK8lFJMuwNLNA1u")
                .role(Role.ADMIN)
                .status(Status.ACTIVE)
                .build();
    }

    public static User invalidUser() {
        return User
                .builder()
                .username("foo")
                .build();
    }
}
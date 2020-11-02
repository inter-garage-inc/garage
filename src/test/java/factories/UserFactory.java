package factories;

import garage.core.entity.User;
import garage.core.entity.user.Role;

public class UserFactory {
    public static User user() {
        return User
                .builder()
                .username("foo")
                .password("bar")
                .role(Role.ADMIN)
                .build();
    }

    public static User invalidUser() {
        return User
                .builder()
                .username("foo")
                .build();
    }
}

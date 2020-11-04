package garage.web.authentication;

import lombok.Getter;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;

@Getter
public class AuthUserDetails extends User {
    private String id;

    public AuthUserDetails(Long id, String username, String password) {
        super(username, password, new ArrayList<>());
        this.id = Long.toString(id); //Because AuthToken class handles only String
    }
}

package garage.web.authentication.payload;

import lombok.Data;

@Data
public class CredentialsRequest {
    private String username;
    private String password;
}

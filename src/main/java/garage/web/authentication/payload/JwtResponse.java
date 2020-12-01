package garage.web.authentication.payload;

import lombok.Data;

@Data
public class JwtResponse {
    private final String token;
}
package garage.web.authentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtil {
    private final static String SECRET_KEY = "G@rAg3Inc";
    private final static Integer EXPIRATION = 1000 * 60 * 60 * 10;

    public String createToken(MyUserDetails myUserDetails) {
        var currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .claim("username", myUserDetails.getUsername())
                .claim("password", myUserDetails.getPassword())
                .claim("user", myUserDetails.getAsString())
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(currentTimeMillis + EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private Boolean isExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractClaim(String token, String claim_key) {
        return extractAllClaims(token).get(claim_key, String.class);
    }

    public Boolean isValid(String token, UserDetails userDetails) {
        return (
                extractClaim(token, "username").equals(userDetails.getUsername())
                &&
                extractClaim(token, "password").equals(userDetails.getPassword())
                &&
                !isExpired(token)
        );
    }
}

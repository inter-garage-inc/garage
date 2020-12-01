package garage.web.authentication;
import garage.core.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${garage.web.authentication.jwt.secret-key}")
    private String secretKey;

    @Value("${garage.web.authentication.jwt.expiration}")
    private Integer expiration;

    public String generateToken(Authentication authentication) {
        var user = (AuthUser) authentication.getPrincipal();

        var now = new Date();
        var expiryDate = new Date(now.getTime() + expiration);

        return Jwts
                .builder()
                .claim("username", user.getUsername())
                .claim("password", user.getPassword())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String getUserUsernameFromJWT(String token) {
        var claims = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("username", String.class);
    }

    public String getPasswordFromJWT(String token) {
        var claims = Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("password", String.class);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            System.err.println("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            System.err.println("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            System.err.println("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            System.err.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.err.println("JWT claims string is empty.");
        }
        return false;
    }
}

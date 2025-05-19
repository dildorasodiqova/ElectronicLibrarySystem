package uz.uzinfocom.electroniclibrarysystem.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import uz.uzinfocom.electroniclibrarysystem.entity.UserEntity;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Component
@Getter
public class JwtUtil {
    //    @Value("${jwt.secret.key}")
//    private String SECRET_KEY;
    private final Key SECRET_KEY_V2= Keys.hmacShaKeyFor("ApeVFjUOyTmk7XYwgcYwIEsix1mALAsQhftYQqWHE8P6kcnqbZv0Uxj9HduKEvjXzz0sVYCG0ZjSBtmxadtAiQ==".getBytes());

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY_V2)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserEntity user) {

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .claim("user", Map.of(
                        "id", user.getId(),
                        "username", user.getUsername(),
                        "fullName", user.getFullName(),
                        "roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()
                ))                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours expiry
                .signWith(SECRET_KEY_V2, SignatureAlgorithm.HS512)
                .compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}



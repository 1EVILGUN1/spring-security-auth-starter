package jwt;

import common.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import properties.JwtProperties;

import javax.crypto.SecretKey;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenService {

    private final SecretKey key;
    private final JwtProperties properties;

    public JwtTokenService(JwtProperties properties, SecretKey key) {
        this.properties = properties;
        this.key = key;
    }

    public Authentication authenticate(String token) {
        try {
            Claims claims = Jwts.parser()
                    .requireIssuer(properties.getIssuer())
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String userId = claims.getSubject();
            List<String> roles = claims.get(
                    properties.getRolesClaim(),
                    List.class
            );
            if (roles == null) {
                roles = Collections.emptyList();
            }

            List<SimpleGrantedAuthority> authorities = roles.stream()
                    .map(r -> new SimpleGrantedAuthority(
                            properties.getRolePrefix() + r
                    )).collect(Collectors.toList());

            UserPrincipal principal = new UserPrincipal(userId);
            return new JwtAuthenticationToken(principal, authorities);

        } catch (Exception e) {
            throw new BadCredentialsException("Invalid JWT token", e);
        }
    }
}

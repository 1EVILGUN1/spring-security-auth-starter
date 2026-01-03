package jwt;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final String token;

    // до аутентификации
    public JwtAuthenticationToken(String token) {
        super(null);
        this.principal = null;
        this.token = token;
        setAuthenticated(false);
    }

    // после
    public JwtAuthenticationToken(
            Object principal,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(authorities);
        this.principal = principal;
        this.token = null;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getToken() {
        return token;
    }
}

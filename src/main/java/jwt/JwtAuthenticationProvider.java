package jwt;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenService tokenService;

    public JwtAuthenticationProvider(JwtTokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
        return tokenService.authenticate(token.getToken());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

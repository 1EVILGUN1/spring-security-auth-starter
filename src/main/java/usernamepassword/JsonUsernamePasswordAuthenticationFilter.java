package usernamepassword;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import java.io.IOException;
import java.util.Map;

public class JsonUsernamePasswordAuthenticationFilter
        extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public JsonUsernamePasswordAuthenticationFilter(
            String loginPath,
            AuthenticationManager authenticationManager,
            ObjectMapper objectMapper
    ) {
        super(loginPath);
        setAuthenticationManager(authenticationManager);
        this.objectMapper = objectMapper;
    }

    @Override
    public org.springframework.security.core.Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {

        Map<String, String> body =
                objectMapper.readValue(request.getInputStream(), Map.class);

        String username = body.get("usernamepassword");
        String password = body.get("password");

        var authRequest =
                new UsernamePasswordAuthenticationToken(username, password);

        return getAuthenticationManager().authenticate(authRequest);
    }
}

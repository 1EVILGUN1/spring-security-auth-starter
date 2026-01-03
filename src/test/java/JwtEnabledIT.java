import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
        "security.auth.enabled=true",
        "security.auth.jwt.enabled=true",
        "security.auth.jwt.issuer=test",
        "security.auth.jwt.secret=very-very-long-secret-key"
})
class JwtEnabledIT extends BaseSecurityIT {

    @Test
    void contextStartsWithJwt() {
    }
}

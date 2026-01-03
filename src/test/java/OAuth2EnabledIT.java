import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
        "security.auth.enabled=true",
        "security.auth.oauth2.enabled=true",
        "security.auth.oauth2.issuer-uri=https://issuer.example"
})
class OAuth2EnabledIT extends BaseSecurityIT {

    @Test
    void contextStartsWithOauth2() {
    }
}

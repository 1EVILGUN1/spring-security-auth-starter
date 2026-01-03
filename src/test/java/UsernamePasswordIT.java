import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
        "security.auth.enabled=true",
        "security.auth.username-password.enabled=true"
})
class UsernamePasswordIT extends BaseSecurityIT {

    @Test
    void contextStartsWithLoginEnabled() {
    }
}

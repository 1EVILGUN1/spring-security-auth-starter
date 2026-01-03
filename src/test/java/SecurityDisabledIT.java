import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
        "security.auth.enabled=false"
})
class SecurityDisabledIT extends BaseSecurityIT {

    @Test
    void contextStartsWithoutSecurity() {
        // если контекст поднялся — тест пройден
    }
}

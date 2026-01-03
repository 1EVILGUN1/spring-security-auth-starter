import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import testapp.TestApplication;

import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtOauth2XorIT {

    @Test
    void jwtAndOauth2CannotBeEnabledTogether() {
        assertThrows(IllegalStateException.class, () -> {
            try (ConfigurableApplicationContext ctx =
                         SpringApplication.run(
                                 TestApplication.class,
                                 "--security.auth.jwt.enabled=true",
                                 "--security.auth.jwt.issuer=test",
                                 "--security.auth.jwt.secret=very-very-long-secret-key",
                                 "--security.auth.oauth2.enabled=true",
                                 "--security.auth.oauth2.issuer-uri=https://issuer.example"
                         )) {
            }
        });
    }
}

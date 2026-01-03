package autoconfigure;

import io.jsonwebtoken.security.Keys;
import jwt.JwtAuthenticationFilter;
import jwt.JwtAuthenticationProvider;
import properties.JwtProperties;
import jwt.JwtTokenService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

@AutoConfiguration
@EnableConfigurationProperties(JwtProperties.class)
@ConditionalOnProperty(prefix = "security.auth.jwt", name = "enabled", havingValue = "true")
public class JwtAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    JwtTokenService jwtTokenService(
            JwtProperties properties,
            SecretKey jwtSecretKey
    ) {
        return new JwtTokenService(properties, jwtSecretKey);
    }


    @Bean
    @ConditionalOnMissingBean
    JwtAuthenticationProvider jwtAuthenticationProvider(JwtTokenService tokenService) {
        return new JwtAuthenticationProvider(tokenService);
    }

    /**
     * Локальный AuthenticationManager только для JWT
     */
    @Bean
    @ConditionalOnMissingBean(name = "jwtAuthenticationManager")
    AuthenticationManager jwtAuthenticationManager(JwtAuthenticationProvider provider) {
        return new ProviderManager(List.of(provider));
    }

    @Bean
    @ConditionalOnMissingBean
    JwtAuthenticationFilter jwtAuthenticationFilter(
            AuthenticationManager jwtAuthenticationManager
    ) {
        return new JwtAuthenticationFilter(jwtAuthenticationManager);
    }

    @Bean
    @ConditionalOnMissingBean
    SecretKey jwtSecretKey(JwtProperties properties) {
        return Keys.hmacShaKeyFor(
                properties.getSecret().getBytes(StandardCharsets.UTF_8)
        );
    }

}

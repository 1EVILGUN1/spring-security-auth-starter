package autoconfigure;

import oauth2.OAuth2JwtGrantedAuthoritiesConverter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import properties.OAuth2Properties;

@AutoConfiguration
@EnableConfigurationProperties(OAuth2Properties.class)
@ConditionalOnProperty(
        prefix = "security.auth.oauth2",
        name = "enabled",
        havingValue = "true"
)
public class OAuth2AutoConfiguration {

    @Bean
    JwtDecoder jwtDecoder(OAuth2Properties properties) {

        if (properties.getIssuerUri() != null) {
            return JwtDecoders.fromIssuerLocation(
                    properties.getIssuerUri()
            );
        }

        if (properties.getJwkSetUri() != null) {
            return NimbusJwtDecoder
                    .withJwkSetUri(properties.getJwkSetUri())
                    .build();
        }

        throw new IllegalStateException(
                "Either issuer-uri or jwk-set-uri must be configured"
        );
    }

    @Bean
    JwtAuthenticationConverter oauth2JwtAuthenticationConverter() {

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(
                new OAuth2JwtGrantedAuthoritiesConverter()
        );

        return converter;
    }

}

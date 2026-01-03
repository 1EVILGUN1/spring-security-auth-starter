package core;

import common.JwtAccessDeniedHandler;
import common.JwtAuthenticationEntryPoint;
import jwt.JwtAuthenticationFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import properties.JwtProperties;
import properties.OAuth2Properties;
import properties.SecurityProperties;
import usernamepassword.JsonUsernamePasswordAuthenticationFilter;

@AutoConfiguration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
@ConditionalOnClass(HttpSecurity.class)
@ConditionalOnProperty(
        prefix = "security.auth",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class SecurityAutoConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtAuthenticationEntryPoint entryPoint,
            JwtAccessDeniedHandler accessDeniedHandler,
            JwtAuthenticationFilter jwtAuthenticationFilter,
            JsonUsernamePasswordAuthenticationFilter usernamePasswordFilter,
            @Nullable JwtAuthenticationConverter oauth2JwtAuthenticationConverter
    ) throws Exception {

        // base config
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(entryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
                );

        // OAuth2 (optional)
        if (oauth2JwtAuthenticationConverter != null) {
            http.oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(jwt -> jwt
                            .jwtAuthenticationConverter(
                                    oauth2JwtAuthenticationConverter
                            )
                    )
            );
        }

        // Filters
        http
                .addFilterBefore(
                        usernamePasswordFilter,
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    SecurityModeValidator securityModeValidator(
            JwtProperties jwtProperties,
            OAuth2Properties oauth2Properties
    ) {
        return new SecurityModeValidator(jwtProperties, oauth2Properties);
    }

}

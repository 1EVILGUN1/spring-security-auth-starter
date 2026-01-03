package autoconfigure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import properties.UsernamePasswordProperties;
import usernamepassword.JsonUsernamePasswordAuthenticationFilter;

import java.util.List;

@AutoConfiguration
@EnableConfigurationProperties(UsernamePasswordProperties.class)
@ConditionalOnProperty(
        prefix = "security.auth.username-password",
        name = "enabled",
        havingValue = "true"
)
public class UsernamePasswordAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @ConditionalOnMissingBean
    DaoAuthenticationProvider daoAuthenticationProvider(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    @ConditionalOnMissingBean(name = "usernamePasswordAuthenticationManager")
    AuthenticationManager usernamePasswordAuthenticationManager(
            DaoAuthenticationProvider provider
    ) {
        return new ProviderManager(List.of(provider));
    }

    @Bean
    JsonUsernamePasswordAuthenticationFilter
    usernamePasswordAuthenticationFilter(
            UsernamePasswordProperties properties,
            AuthenticationManager usernamePasswordAuthenticationManager,
            ObjectMapper objectMapper
    ) {
        return new JsonUsernamePasswordAuthenticationFilter(
                properties.getLoginPath(),
                usernamePasswordAuthenticationManager,
                objectMapper
        );
    }
}

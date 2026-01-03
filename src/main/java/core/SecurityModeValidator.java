package core;

import jakarta.annotation.PostConstruct;
import properties.JwtProperties;
import properties.OAuth2Properties;

public class SecurityModeValidator {
    private final JwtProperties jwtProperties;
    private final OAuth2Properties oauth2Properties;

    public SecurityModeValidator(
            JwtProperties jwtProperties,
            OAuth2Properties oauth2Properties
    ) {
        this.jwtProperties = jwtProperties;
        this.oauth2Properties = oauth2Properties;
    }

    @PostConstruct
    void validate() {
        if (jwtProperties.isEnabled() && oauth2Properties.isEnabled()) {
            throw new IllegalStateException(
                    "Invalid security configuration: JWT and OAuth2 " +
                            "cannot be enabled at the same time. Choose one."
            );
        }
    }
}

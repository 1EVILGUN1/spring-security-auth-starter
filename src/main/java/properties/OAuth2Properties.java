package properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.auth.oauth2")
public class OAuth2Properties {

    /**
     * Включить OAuth2 Resource Server
     */
    private boolean enabled = false;

    /**
     * Issuer URI (OIDC)
     * Пример: https://accounts.google.com
     */
    private String issuerUri;

    /**
     * JWK Set URI (если issuer не используется)
     */
    private String jwkSetUri;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getIssuerUri() {
        return issuerUri;
    }

    public void setIssuerUri(String issuerUri) {
        this.issuerUri = issuerUri;
    }

    public String getJwkSetUri() {
        return jwkSetUri;
    }

    public void setJwkSetUri(String jwkSetUri) {
        this.jwkSetUri = jwkSetUri;
    }

    // getters / setters
}

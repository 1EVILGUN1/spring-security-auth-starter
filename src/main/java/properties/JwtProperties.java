package properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.auth.jwt")
public class JwtProperties {

    /**
     * Включить JWT-аутентификацию
     */
    private boolean enabled = false;

    /**
     * Issuer для проверки токена
     */
    private String issuer;

    /**
     * Secret (если HMAC)
     */
    private String secret;

    /**
     * Public key (если RSA)
     */
    private String publicKey;

    /**
     * Claim с ролями
     */
    private String rolesClaim = "roles";

    /**
     * Префикс для ролей
     */
    private String rolePrefix = "ROLE_";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getRolesClaim() {
        return rolesClaim;
    }

    public void setRolesClaim(String rolesClaim) {
        this.rolesClaim = rolesClaim;
    }

    public String getRolePrefix() {
        return rolePrefix;
    }

    public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }
}

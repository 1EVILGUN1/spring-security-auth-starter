package properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.auth.username-password")
public class UsernamePasswordProperties {

    /**
     * Включить username/password login
     */
    private boolean enabled = false;

    /**
     * Endpoint логина
     */
    private String loginPath = "/login";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getLoginPath() {
        return loginPath;
    }

    public void setLoginPath(String loginPath) {
        this.loginPath = loginPath;
    }
}

package common;

public class UserPrincipal {
    private final String userId;

    public UserPrincipal(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}

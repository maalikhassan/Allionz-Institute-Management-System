package model;

public class AdminUserSession {

    private static AdminUserSession instance;
    private String username;
    private String fullName;

    private AdminUserSession() {
    }

    public static AdminUserSession getInstance() {
        if (instance == null) {
            instance = new AdminUserSession();
        }
        return instance;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String fullname) {
        this.fullName = fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return fullName;
    }

    public void logout() {
        username = null;
    }

    public boolean isLoggedin() {
        return username != null;
    }

}

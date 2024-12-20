package model;

public class AcademicUserSession {
    
    private static AcademicUserSession instance;
    private String username;
    private String fullName;
    
    private AcademicUserSession() {}
    
    public static AcademicUserSession getInstance() {
        if (instance == null) {
            instance = new AcademicUserSession();
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


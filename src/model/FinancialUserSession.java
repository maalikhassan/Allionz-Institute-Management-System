package model;

public class FinancialUserSession {
    
    private static FinancialUserSession instance;
    private String username;
    private String fullName;
    
    private FinancialUserSession() {}
    
    public static FinancialUserSession getInstance() {
        if (instance == null) {
            instance = new FinancialUserSession();
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

package model;

public class CredentialValidator {

    public String validate(String email, String password) {
        if (email.isEmpty()) {
            return "Please Enter your Email Address";
        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return "Invalid Email Address";
        } else if (password.isEmpty()) {
            return "Please Enter your Password";
        } else if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            return "Invalid Password";
        }
        return "Valid";
    }
}

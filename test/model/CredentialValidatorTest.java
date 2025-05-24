package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CredentialValidatorTest {

    private CredentialValidator validator;

    @Before
    public void setUp() {
        validator = new CredentialValidator();
    }

    @After
    public void tearDown() {
        validator = null;
    }

    @Test
    public void testValidateSuccess() {
        String email = "john.doe@example.com";
        String password = "John@2023";
        String expected = "Valid";
        String result = validator.validate(email, password);
        assertEquals(expected, result);
    }

    @Test
    public void testInvalidEmail() {
        String email = "bademail.com";
        String password = "Test@1234";
        String expected = "Invalid Email Address";
        String result = validator.validate(email, password);
        assertEquals(expected, result);
    }

    @Test
    public void testEmptyPassword() {
        String email = "john.doe@example.com";
        String password = "";
        String expected = "Please Enter your Password";
        String result = validator.validate(email, password);
        assertEquals(expected, result);
    }

    @Test
    public void testInvalidPassword() {
        String email = "john.doe@example.com";
        String password = "pass";  // too weak
        String expected = "Invalid Password";
        String result = validator.validate(email, password);
        assertEquals(expected, result);
    }
}

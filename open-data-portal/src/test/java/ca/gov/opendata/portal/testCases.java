package phase2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
public class testCases {

    @Test
    public void testGettersAndSetters() {
        // Create a User instance
        User user = new User("testUser", "test@example.com", "password");

        // Test getters
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());

        // Test setters
        user.setUsername("newUsername");
        user.setEmail("new@example.com");
        user.setPassword("newPassword");

        assertEquals("newUsername", user.getUsername());
        assertEquals("new@example.com", user.getEmail());
        assertEquals("newPassword", user.getPassword());
    }

    @Test
    public void testRegisterUser() {
        // Create a UserSignUp instance
        UserSignUp signUp = new UserSignUp();

        // Test registration with unique username and email
        assertTrue(signUp.registerUser("newUser", "new@example.com", "password"));

        // Test registration with existing username
        assertFalse(signUp.registerUser("testUser", "existing@example.com", "password"));

        // Test registration with existing email
        assertFalse(signUp.registerUser("existingUser", "test@example.com", "password"));
    }

    @Test
    public void testAuthenticateUser() {
        // Create a UserAuthenticator instance
        UserAuthenticator authenticator = new UserAuthenticator();

        // Test authentication with valid credentials
        assertTrue(authenticator.authenticateUser("testUser", "password"));

        // Test authentication with invalid credentials
        assertFalse(authenticator.authenticateUser("invalidUser", "invalidPassword"));
    }
}

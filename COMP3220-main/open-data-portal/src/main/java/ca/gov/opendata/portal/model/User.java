package ca.gov.opendata.portal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents a user in the system.
 */
@Document(collection = "User") // Use lowercase for collection name
public class User {

    @Id
    private String id; // Primary key field

    private String username;
    private String email;
    private String password;

    /**
     * Default constructor for User.
     */
    public User() {
    }

    /**
     * Constructs a new User object with the specified attributes.
     *
     * @param username the username of the user
     * @param email    the email of the user
     * @param password the password of the user
     */
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the username of the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email the email of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}


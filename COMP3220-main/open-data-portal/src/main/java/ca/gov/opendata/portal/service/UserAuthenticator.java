package ca.gov.opendata.portal.service;

import ca.gov.opendata.portal.model.User;
import ca.gov.opendata.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Service class for authenticating users.
 * This service is responsible for validating user credentials against stored information
 * in the user repository.
 */
@Service
public class UserAuthenticator {

    private final UserRepository userRepository;

    /**
     * Constructs a UserAuthenticator with a UserRepository.
     * 
     * @param userRepository The repository containing user data, used for authentication checks.
     */
    @Autowired
    public UserAuthenticator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Attempts to authenticate a user based on a username and password.
     * 
     * This method checks if a user exists with the given username and then compares
     * the provided password with the stored password for that user.
     * 
     * @param username The username of the user attempting to authenticate.
     * @param password The password provided by the user.
     * @return true if the username exists and the password matches; false otherwise.
     */
    public boolean authenticate(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
}

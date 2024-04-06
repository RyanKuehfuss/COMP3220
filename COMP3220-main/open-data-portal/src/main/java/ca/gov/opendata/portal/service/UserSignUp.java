package ca.gov.opendata.portal.service;

import ca.gov.opendata.portal.model.User;
import ca.gov.opendata.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserSignUp {

    private final UserRepository userRepository;

    @Autowired
    public UserSignUp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Registers a new user in the system.
     *
     * @param username The username of the new user.
     * @param email The email of the new user.
     * @param password The password of the new user.
     * @return true if the user was successfully registered, false if the username or email already exists.
     */
    public boolean registerUser(String username, String email, String password) {
        // Check if the username or email already exists
        Optional<User> existingUser = userRepository.findByUsername(username);
        Optional<User> existingEmail = userRepository.findByEmail(email);
        if (existingUser.isPresent() || existingEmail.isPresent()) {
            // User already exists
            return false;
        } else {
            // Save the new user
            User newUser = new User(username, email, password);
            userRepository.save(newUser);
            return true;
        }
    }
}


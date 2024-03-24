package ca.gov.opendata.portal.controller;

import ca.gov.opendata.portal.model.User;
import ca.gov.opendata.portal.service.UserSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    private final UserSignUp userSignUp;

    @Autowired
    public SignupController(UserSignUp userSignUp) {
        this.userSignUp = userSignUp;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody User user) {
        // Assuming your User model has username, email, and password fields
        boolean isRegistered = userSignUp.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        
        if (isRegistered) {
            return ResponseEntity.ok("Signup successful.");
        } else {
            return ResponseEntity.badRequest().body("Signup failed: user with the same username or email already exists.");
        }
    }
}


package ca.gov.opendata.portal.controller;

import ca.gov.opendata.portal.model.User;
import ca.gov.opendata.portal.service.UserSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.ResponseEntity;

@Controller
public class SignupController {

    private final UserSignUp userSignUpService;

    @Autowired
    public SignupController(UserSignUp userSignUpService) {
        this.userSignUpService = userSignUpService;
    }


    // Process the signup form submission
    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody User user) {
        boolean signUpSuccess = userSignUpService.registerUser(user.getUsername(), user.getEmail(), user.getPassword());
        if (signUpSuccess) {
            // Redirect to login page or a success page
            return ResponseEntity.ok("User successfully registered.");
        } else {
            return ResponseEntity.badRequest().body("Signup failed. User might already exist.");
        }
    }
}

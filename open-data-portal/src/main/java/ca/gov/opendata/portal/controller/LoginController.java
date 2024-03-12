package ca.gov.opendata.portal.controller;

import ca.gov.opendata.portal.model.User;
import ca.gov.opendata.portal.service.UserAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@RestController
public class LoginController {

    private final UserAuthenticator userAuthenticator;

    @Autowired
    public LoginController(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }

    @GetMapping("/login")
public String loginForm(Model model) {
    model.addAttribute("user", new User()); // Ensures a User object is available to the form
    return "login"; // Returns the name of the Thymeleaf template for the login form
}


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean isAuthenticated = userAuthenticator.authenticateUser(user.getUsername(), user.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.badRequest().body("Invalid username or password.");
        }
    }
}


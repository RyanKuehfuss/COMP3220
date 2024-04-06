package ca.gov.opendata.portal.controller;

import ca.gov.opendata.portal.service.UserSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller class for handling user signup functionality.
 */
@Controller
public class SignupController {

    private final UserSignUp userSignUp;

    /**
     * Constructor for SignupController.
     * @param userSignUp The service for signing up users.
     */
    @Autowired
    public SignupController(UserSignUp userSignUp) {
        this.userSignUp = userSignUp;
    }

    /**
     * Display the signup form.
     * @return The name of the HTML file containing the signup form.
     */
    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup"; // Assuming your signup form is named signup.html
    }

    /**
     * Process user signup.
     * @param username The username entered by the user.
     * @param email The email entered by the user.
     * @param password The password entered by the user.
     * @param redirectAttributes Redirect attributes to add flash attributes.
     * @return The redirect path based on signup success or failure.
     */
    @PostMapping("/signup")
    public String signUpUser(@RequestParam("username") String username,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             RedirectAttributes redirectAttributes) {
        boolean success = userSignUp.registerUser(username, email, password);

        if (success) {
            System.out.println("Account successfully created.");
            return "redirect:/login"; // Redirect to the login page or another appropriate page
        } else {
            redirectAttributes.addFlashAttribute("error", "User with the same username or email already exists.");
            return "redirect:/signup"; // Stay on the signup page and show an error message
        }
    }
}






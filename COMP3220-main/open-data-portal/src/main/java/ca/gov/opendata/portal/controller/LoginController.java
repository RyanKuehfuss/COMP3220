package ca.gov.opendata.portal.controller;

import ca.gov.opendata.portal.service.UserAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller class for handling user login functionality.
 */
@Controller
public class LoginController {

    private final UserAuthenticator userAuthenticator;

    /**
     * Constructor for LoginController.
     * @param userAuthenticator The service for authenticating users.
     */
    @Autowired
    public LoginController(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }

    /**
     * Display the login form.
     * @return The name of the HTML file containing the login form.
     */
    @GetMapping("/login")
    public String showLoginForm() {
        // Return the name of the HTML file containing the login form
        return "login"; // Assuming your login form is named login.html
    }

    /**
     * Process user login.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @param redirectAttributes Redirect attributes to add flash attributes.
     * @return ModelAndView to redirect to the appropriate page.
     */
    @PostMapping("/login")
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        boolean isAuthenticated = userAuthenticator.authenticate(username, password);

        if (isAuthenticated) {
            // Redirect to /web/data/list upon successful login
            return new ModelAndView(new RedirectView("/web/data/list"));
        } else {
            // Add error message and redirect back to the login page
            redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
            return new ModelAndView(new RedirectView("/login"));
        }
    }
}

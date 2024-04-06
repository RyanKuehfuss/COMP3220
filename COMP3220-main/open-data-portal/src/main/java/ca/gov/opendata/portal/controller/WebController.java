package ca.gov.opendata.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling web page navigation.
 */
@Controller
public class WebController {

    /**
     * Display the home page.
     * @return The name of the HTML file containing the home page.
     */
    @GetMapping("/")
    public String showHomePage() {
        // Return the name of the HTML file containing the options
        return "index"; 
    }
}
package ca.gov.opendata.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class to start the Open Data Portal application.
 */
@SpringBootApplication
public class OpenDataPortalApplication {

    /**
     * Main method to start the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(OpenDataPortalApplication.class, args);
    }
}

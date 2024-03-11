package phase2;
import java.util.Scanner;


public class app {
    // command line application to test code and database
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter a username:");
        String username = scanner.nextLine();
        
        System.out.println("Please enter your email:");
        String email = scanner.nextLine();
        
        System.out.println("Please enter a password:");
        String password = scanner.nextLine();
       
        UserSignUp userSignUp = new UserSignUp();
        boolean registrationStatus = userSignUp.registerUser(username, email, password);

        if (registrationStatus) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("User registration failed!");
        }

        UserAuthenticator authenticator = new UserAuthenticator();
        boolean authStatus = authenticator.authenticateUser("username", "password");

        if (authStatus) {
            System.out.println("User authentication successful!");
        } else {
            System.out.println("User authentication failed!");
        }
    }
}


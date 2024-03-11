package phase2;

import java.util.Scanner;

public class app {
   
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome! Please choose an option:");
        System.out.println("1: Sign up");
        System.out.println("2: Login");
        System.out.print("Enter choice (1 or 2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice == 1) {
            // Sign up case
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
        } else if (choice == 2) {
            // Login case
            System.out.println("Please enter your username:");
            String username = scanner.nextLine();

            System.out.println("Please enter your password:");
            String password = scanner.nextLine();

            UserAuthenticator authenticator = new UserAuthenticator();
            boolean authStatus = authenticator.authenticateUser(username, password);

            if (authStatus) {
                System.out.println("User authentication successful!");
            } else {
                System.out.println("User authentication failed!");
            }
        } else {
            System.out.println("Invalid option. Please run the application again.");
        }

        scanner.close();
    }
}



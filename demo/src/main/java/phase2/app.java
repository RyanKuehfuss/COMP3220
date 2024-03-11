package phase2;

import java.util.Scanner;

public class app {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Welcome! Please choose an option:");
            System.out.println("1: Sign up");
            System.out.println("2: Login");
            System.out.println("3: Exit");
            System.out.print("Enter choice (1, 2, or 3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    // Sign up case
                    boolean signUpComplete = false;
                    while (!signUpComplete) {
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
                            signUpComplete = true;
                        } else {
                            System.out.println("User registration failed! Username or email might already be in use.");
                        }
                    }
                    break;
                case 2:
                    // Login case
                    boolean loginComplete = false;
                    while (!loginComplete) {
                        System.out.println("Please enter your username:");
                        String username = scanner.nextLine();

                        System.out.println("Please enter your password:");
                        String password = scanner.nextLine();

                        UserAuthenticator authenticator = new UserAuthenticator();
                        boolean authStatus = authenticator.authenticateUser(username, password);

                        if (authStatus) {
                            System.out.println("User authentication successful!");
                            loginComplete = true;
                        } else {
                            System.out.println("User authentication failed! Please check your username and password.");
                        }
                    }
                    break;
                case 3:
                    // Exit case
                    System.out.println("Exiting application.");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select 1, 2, or 3.");
                    break;
            }
        }

        scanner.close();
    }
}




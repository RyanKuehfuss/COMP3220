package phase2;

public class app {
    // command line application to test code and database
    public static void main(String[] args) {
        UserSignUp userSignUp = new UserSignUp();
        boolean registrationStatus = userSignUp.registerUser("username", "user@example.com", "password");

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


package ca.gov.opendata.portal;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import ca.gov.opendata.portal.model.Data;
import ca.gov.opendata.portal.service.UserAuthenticator;
import ca.gov.opendata.portal.service.UserSignUp;

@SpringBootApplication
public class OpenDataPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenDataPortalApplication.class, args);
		
		Scanner scanner = new Scanner(System.in);
		int choice;
		boolean signUpComplete;
		boolean loginComplete;
		boolean registrationStatus;
		boolean authStatus;
		boolean running = true;
		String username = "";
		
		// Loop until the user chooses to exit
		while(running) {
			
			System.out.printf("Would you like to login or register?\n1. Register\n2. Login\n3. Exit\n> ");
			choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			// User selected Register option
			case 1:
				UserSignUp userSignUp = new UserSignUp();
				signUpComplete = false;
                while (!signUpComplete) {
                    System.out.printf("Please enter a username: ");
                    username = scanner.nextLine();

                    System.out.printf("\nPlease enter your email: ");
                    String email = scanner.nextLine();

                    System.out.printf("\nPlease enter a password: ");
                    String password = scanner.nextLine();
                    System.out.println();
          
                    registrationStatus = userSignUp.registerUser(username, email, password);

                    if (registrationStatus) {
                        System.out.println("User registered successfully!");
                        signUpComplete = true;
                    } else {
                        System.out.println("User registration failed! Username or email might already be in use.");
                    }
                }
			// User selected Login option
			case 2:
				UserAuthenticator authenticator = new UserAuthenticator();
				loginComplete = false;
                while (!loginComplete) {
                    System.out.println("Please enter your username:");
                    username = scanner.nextLine();

                    System.out.println("Please enter your password:");
                    String password = scanner.nextLine();

                    authStatus = authenticator.authenticateUser(username, password);

                    if (authStatus) {
                        System.out.println("User authentication successful!");
                        loginComplete = true;
                    } else {
                        System.out.println("User authentication failed! Please check your username and password.");
                    }
                }
                
                if(loginComplete) {
                	boolean signedIn = true;
                	int dataEditChoice = -1;
                	ResponseEntity<Data> responseEntity;
                	
                	while(signedIn) {                		
                		System.out.printf("What would you like to do?\n1. Create a dataset\n2. Get all datasets\n3. Get single dataset by ID\n4. Update a dataset\n5. Delete a dataset\n6. Logout\n> ");
                		dataEditChoice = scanner.nextInt();
                		scanner.nextLine();
                		
                		switch(dataEditChoice) {
                		// User selects create dataset
                		case 1:
                			String datasetName;
                			String datasetDescription;
                			String datasetID;
                			String datasetFilePath;
                			String apiURL = "http://localhost:8080/api/data";
                			RestTemplate restTemplate = new RestTemplate();
                			
                			System.out.printf("Enter the name of the dataset: ");
                			datasetName = scanner.nextLine();
                			
                			System.out.printf("Enter the description of the dataset: ");
                			datasetDescription = scanner.nextLine();
                			
                			System.out.printf("Enter the file path of the dataset: ");
                			datasetFilePath = scanner.nextLine();
                			
                			System.out.printf("Enter the ID of the dataset: ");
                			datasetID = scanner.nextLine();
                			
                			Data dataset = new Data(datasetName, datasetDescription, datasetFilePath, datasetID, username);
                			
                			responseEntity = restTemplate.postForEntity(apiURL, dataset, Data.class);
                			
                			if(responseEntity.getStatusCode().is2xxSuccessful()) {
                				System.out.printf("The dataset was successfully created\n");
                			}else {
                				System.out.printf("Error. Dataset not successfully created.\n");
                			}
                		// User selects get all datasets
                		case 2:
                		
                		// User selects get dataset by ID
                		case 3:
                			
                		// User selects update a dataset
                		case 4:
                			
                		// User selects delete a dataset
                		case 5:
                			
                		// User selects logout
                		case 6:
                			System.out.println("Logging out.");
            				signedIn = false;
            				break;
            			// Error in getting selection
                		default:
                			System.out.println("Error. Invalid selection. Please select again.\n");
                            break;
                		}
                		
                		
                	}
                	
                }
                break;
			// User selects exit
			case 3:
				System.out.println("Exiting application.");
				running = false;
				break;
			// Error in getting selection
			default:
				System.out.println("Error. Invalid selection. Please select again.\n");
                break;
			}
		}
		scanner.close();
	}

}

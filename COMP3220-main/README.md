# COMP3220
Open Data Portal Application 

1. Service Classes:
   - DataOperationService.java: Handles CRUD operations for Data entities, including file upload and download functionalities.
   - UserAuthenticator.java: Manages user authentication by validating usernames and passwords against stored credentials.
   - UserSignUp.java: Responsible for registering new users, ensuring that usernames and emails are unique.

2. Model Classes:
   - Data.java: Represents the data entities within the portal, likely encapsulating various datasets.
   - User.java: Represents user entities, including information such as usernames, passwords, and email addresses.

3. Repository Interfaces:
   - DataRepository.java: Spring Data JPA repository interface for Data entities, facilitating database interactions.
   - UserRepository.java: Manages database operations for User entities, such as searching by username or email.

4. Controller Classes:
   - DataController.java: Manages web requests related to data operations like listing, adding, or removing datasets.
   - LoginController.java: Handles web requests for the user login process.
   - SearchController.java: Manages search functionalities across the datasets in the portal.
   - SignupController.java: Handles web requests for the user registration process.
   - WebController.java: General controller for web interactions, potentially dealing with navigation and static pages.
   - WebDataController.java: Similar to DataController but might offer more detailed views or specialized data handling.

5. Main Application Class:
   - OpenDataPortalApplication.java: Bootstraps the Spring Boot application, setting up the web application context.

6. HTML files:
   - data-details.html: This file is responsible for displaying details of a specific data entry, including title, description, and file content if available. It also provides links to view, edit, and delete the data entry.
   - data-form.html: This file contains a form for creating a new data entry. It includes fields for title, description, uploading a file, and specifying an owner ID. Upon submission, it sends data to the server for processing.
   - data-list.html: This file displays a list of data entries with their titles, descriptions, and action links for viewing, editing, and deleting each entry. It also provides links for adding new data and searching existing data.
   - index.html: This is the homepage of the portal. It offers options for signing up, logging in, and accessing the data list for guests.
   - login.html: This file contains a form for user login, including fields for username and password.
   - search-form.html: This file contains a form for searching data entries based on title, owner ID, and description
   - search-results.html: This file displays the results of a search query, showing the title, description, and action links  for each matching data entry.
   - signup.html: This file contains a form for user signup, including fields for username, email, and password.
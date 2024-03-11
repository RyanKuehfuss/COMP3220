Read me for of 1st iteration of User Registration

Overview

This is an early version of a web application's user registration process. The system is made out of Java files that use MongoDB as the database to implement user registration and authentication features.

Files:

`App.java`

The primary technique for starting the user registration and authentication procedures is contained in this file. It shows you how to register and authenticate users using the `UserSignUp` and `UserAuthenticator` classes.

`UserSignUp.java`

It is the responsibility of this class to register users. After making a connection to a MongoDB database, it adds user data to the "users" collection.

`UserAuthenticator.java`

User authentication is managed by this class. It confirms the entered password and username by retrieving user data from the MongoDB database.

`User.java`

The `User` class, defined in this file, represents a user entity with attributes such as username, email, and password.

Dependencies

- MongoDB Java Driver: This driver is used to communicate with and connect to MongoDB databases.

Configuration:

1. In case you haven't previously, install MongoDB on your machine.
2. Verify that MongoDB is operating at port {27017} on ‘localhost’.
3. To verify the user registration and authentication procedures, compile and execute the `App.java` file.

Usage:

Launch the ‘App.java’ file first.
Using the username, email address, and password entered by the user, the application will try to register the user.
After that, the user will be authenticated using their login credentials.
The console will display the authentication and registration statuses.

Note

Since this is an early version of the user registration process, it currently lacks some functionalities, like validation of input or a UI.

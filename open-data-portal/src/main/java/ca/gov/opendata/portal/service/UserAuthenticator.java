package ca.gov.opendata.portal.service;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

@service
public class UserAuthenticator {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public UserAuthenticator() {
        // Connect to MongoDB server
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Access "userdb" database
        database = mongoClient.getDatabase("userdb");
    }

    public boolean authenticateUser(String username, String password) {
        // Get user document from database based on username
        Document userDocument = database.getCollection("users").find(eq("username", username)).first();
        
        // Check if user exists and password matches
        return userDocument != null && userDocument.getString("password").equals(password);
    }
}

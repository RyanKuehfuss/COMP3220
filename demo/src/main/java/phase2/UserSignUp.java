package phase2;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class UserSignUp {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public UserSignUp() {
        // Connect to MongoDB server
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        // Access "userdb" database
        database = mongoClient.getDatabase("userdb");
    }

    public boolean registerUser(String username, String email, String password) {
        // Check if user already exists (not implemented for simplicity)
        
        // Create a document to represent the user
        Document userDocument = new Document("username", username)
                                    .append("email", email)
                                    .append("password", password);
        
        // Insert the document into the "users" collection
        database.getCollection("users").insertOne(userDocument);
        
        return true;
    }
}

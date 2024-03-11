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
        //check to see if user already exists
           long count = database.getCollection("users")
                .countDocuments(Filters.or(
                        Filters.eq("username", username),
                        Filters.eq("email", email)
                ));
        if (count > 0) {
            // User already exists
            System.out.println("A user with the same username or email already exists.");
            return false;
        }
        
        // Create a document to represent the user
        Document userDocument = new Document("username", username)
                                    .append("email", email)
                                    .append("password", password);
        
        // Insert the document into the "users" collection
        database.getCollection("users").insertOne(userDocument);
        System.out.println("Account succesfully created.");
        return true;
    }
}

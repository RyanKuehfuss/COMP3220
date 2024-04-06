package ca.gov.opendata.portal.repository;

import ca.gov.opendata.portal.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing User entities in the database.
 */
public interface UserRepository extends MongoRepository<User, String> {
    /**
     * Finds a user by their username.
     *
     * @param username the username of the user
     * @return an optional containing the user if found, empty otherwise
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user
     * @return an optional containing the user if found, empty otherwise
     */
    Optional<User> findByEmail(String email);

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all users
     */
    List<User> findAll();
}

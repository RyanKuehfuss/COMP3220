package ca.gov.opendata.portal.repository;

import ca.gov.opendata.portal.model.Data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Repository interface for managing Data entities in the database.
 */
public interface DataRepository extends MongoRepository<Data, String> {
    /**
     * Finds all datasets by their title.
     *
     * @param title the title of the dataset
     * @return a list of datasets with the specified title
     */
    List<Data> findByTitle(String title);

    /**
     * Finds all datasets by a specific owner.
     *
     * @param ownerId the ID of the owner
     * @return a list of datasets owned by the specified owner
     */
    List<Data> findByOwnerId(String ownerId);

    /**
     * Finds datasets with a description that contains a specific text (case insensitive).
     *
     * @param description the text to search for in the description
     * @return a list of datasets with descriptions containing the specified text
     */
    List<Data> findByDescriptionContainingIgnoreCase(String description);

    /**
     * Custom query to find datasets by title using regex for case-insensitive search.
     *
     * @param title the title pattern to search for
     * @return a list of datasets with titles matching the specified pattern
     */
    @Query("{'title': {$regex: ?0, $options: 'i'}}")
    List<Data> findByTitleRegex(String title);
}

package ca.gov.opendata.portal.repository;

import ca.gov.opendata.portal.model.Data;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DataRepository extends MongoRepository<Data, String> {
    // Find all datasets by the title
    List<Data> findByTitle(String title);

    // Find all datasets by a specific owner
    List<Data> findByOwnerId(String ownerId);

    // Find datasets with a description that contains a specific text (case insensitive)
    List<Data> findByDescriptionContainingIgnoreCase(String description);

    // Custom query to find datasets by title using @Query with a regex for case-insensitive search
    @Query("{'title': {$regex: ?0, $options: 'i'}}")
    List<Data> findByTitleRegex(String title);

    
}

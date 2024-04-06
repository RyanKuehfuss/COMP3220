package ca.gov.opendata.portal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents a piece of data in the system.
 */
@Setter
@Getter
@Document(collection="Data")
public class Data {
    @Id
    private String id;
    private String title;
    private String description;
    private String filePath;
    private String ownerId;

    // Default constructor needed by Spring Data
    public Data() {
    }

    /**
     * Constructs a new Data object with the specified attributes.
     *
     * @param title       the title of the data
     * @param description the description of the data
     * @param filePath    the file path of the data
     * @param id          the ID of the data
     * @param ownerId     the ID of the owner of the data
     */
    public Data(String title, String description, String filePath, String id, String ownerId) {
        this.title = title;
        this.description = description;
        this.filePath = filePath;
        this.id = id;
        this.ownerId = ownerId;
    }

    // Getters

    /**
     * Retrieves the ID of the data.
     *
     * @return the ID of the data
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the title of the data.
     *
     * @return the title of the data
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the description of the data.
     *
     * @return the description of the data
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the file path of the data.
     *
     * @return the file path of the data
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Retrieves the owner ID of the data.
     *
     * @return the owner ID of the data
     */
    public String getOwnerId() {
        return ownerId;
    }

    // Setters

    /**
     * Sets the ID of the data.
     *
     * @param id the ID of the data
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the title of the data.
     *
     * @param title the title of the data
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the description of the data.
     *
     * @param description the description of the data
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the file path of the data.
     *
     * @param filePath the file path of the data
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Sets the owner ID of the data.
     *
     * @param ownerId the owner ID of the data
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    // toString() method for debugging purposes
    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", filePath='" + filePath + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}



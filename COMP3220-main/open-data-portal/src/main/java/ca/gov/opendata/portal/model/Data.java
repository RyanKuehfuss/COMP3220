package ca.gov.opendata.portal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

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

    // Constructor with parameters
    public Data(String title, String description, String filePath, String id, String ownerId) {
        this.title = title;
        this.description = description;
        this.filePath = filePath;
        this.id = id;
        this.ownerId = ownerId;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getOwnerId() {
        return ownerId;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

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


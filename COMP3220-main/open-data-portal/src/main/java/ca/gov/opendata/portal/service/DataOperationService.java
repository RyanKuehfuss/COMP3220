package ca.gov.opendata.portal.service;

import ca.gov.opendata.portal.model.Data;
import ca.gov.opendata.portal.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Service for managing data operations including CRUD functionalities, and file upload and download for {@link Data} entities.
 * This service interacts with the {@link DataRepository} for persistence operations and manages file storage operations in a specified directory.
 */
@Service
public class DataOperationService {

    private final DataRepository dataRepository;
    private final Path fileStorageLocation;

    /**
     * Constructs a DataOperationService with a specified {@link DataRepository}.
     * Initializes the file storage location where uploaded files will be stored.
     *
     * @param dataRepository The data repository used for data persistence operations.
     * @throws RuntimeException If the file storage directory cannot be created.
     */
    @Autowired
    public DataOperationService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    /**
     * Retrieves all {@link Data} entities from the repository.
     * 
     * @return A list of all {@link Data} entities.
     */
    public List<Data> listAllData() {
        return dataRepository.findAll();
    }

    /**
     * Saves or updates a {@link Data} entity in the repository.
     * 
     * @param data The {@link Data} entity to be saved or updated.
     */
    public void saveOrUpdateData(Data data) {
        dataRepository.save(data);
    }

    /**
     * Finds a {@link Data} entity by its ID.
     * 
     * @param id The ID of the {@link Data} entity to find.
     * @return The found {@link Data} entity.
     * @throws IllegalArgumentException If no {@link Data} entity with the given ID is found.
     */
    public Data findDataById(String id) {
        return dataRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid data Id: " + id));
    }

    /**
     * Deletes a {@link Data} entity and its associated file from the file system, if any.
     * 
     * @param data The {@link Data} entity to delete.
     * @throws IOException If an error occurs during file deletion.
     */
    public void deleteData(Data data) throws IOException {
        if (data.getFilePath() != null && !data.getFilePath().isEmpty()) {
            Path filePath = Paths.get(data.getFilePath());
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        }
        dataRepository.delete(data);
    }

    /**
     * Reads the file content from the file system for a given file path.
     * 
     * @param filePath The path of the file to read.
     * @return The content of the file as a String.
     * @throws RuntimeException If an error occurs during file reading.
     */
    public String readFileContent(String filePath) {
        Path path = Paths.get(filePath);
        try {
            byte[] fileContent = Files.readAllBytes(path);
            return new String(fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content", e);
        }
    }

    /**
     * Uploads a file and associates it with a {@link Data} entity by updating the entity's file path.
     * 
     * @param file The file to upload.
     * @param data The {@link Data} entity with which the file will be associated.
     * @throws IOException If an error occurs during file upload.
     */
    public void uploadFile(MultipartFile file, Data data) throws IOException {
        if (!file.isEmpty()) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);

            data.setFilePath(targetLocation.toString());
            dataRepository.save(data);
        }
    }

    /**
     * Downloads a file associated with a {@link Data} entity.
     * 
     * @param id The ID of the {@link Data} entity whose file is to be downloaded.
     * @return A {@link ResponseEntity} containing the file as a {@link Resource}.
     * @throws RuntimeException If the file cannot be read.
     */
    public ResponseEntity<Resource> downloadFile(String id) throws IOException {
        Data data = findDataById(id);
        Path filePath = Paths.get(data.getFilePath()).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if (resource.exists() && resource.isReadable()) {
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            return ResponseEntity.ok()
                                 .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                                 .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                                 .body(resource);
        } else {
            throw new RuntimeException("Could not read the file.");
        }
    }
}

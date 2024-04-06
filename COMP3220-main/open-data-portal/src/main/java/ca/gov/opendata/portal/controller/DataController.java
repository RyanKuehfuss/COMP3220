package ca.gov.opendata.portal.controller;

import ca.gov.opendata.portal.model.Data;
import ca.gov.opendata.portal.repository.DataRepository;
import ca.gov.opendata.portal.service.DataOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * REST controller for managing {@link Data} entities.
 * This controller provides endpoints for CRUD operations and file handling
 * related to Data entities.
 */
@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataRepository dataRepository;
    private final DataOperationService dataOperationService;

    /**
     * Constructs a DataController with the necessary dependencies.
     *
     * @param dataRepository       A repository for performing CRUD operations on Data entities.
     * @param dataOperationService A service for handling operations that involve more complex business logic.
     */
    @Autowired
    public DataController(DataRepository dataRepository, DataOperationService dataOperationService) {
        this.dataRepository = dataRepository;
        this.dataOperationService = dataOperationService;
    }

    /**
     * Creates a new Data entity.
     *
     * @param data The Data entity to be created.
     * @return A ResponseEntity containing the created Data entity.
     */
    @PostMapping
    public ResponseEntity<Data> createData(@RequestBody Data data) {
        Data savedData = dataRepository.save(data);
        return ResponseEntity.ok(savedData);
    }

    /**
     * Retrieves all Data entities.
     *
     * @return A ResponseEntity containing a list of all Data entities.
     */
    @GetMapping
    public ResponseEntity<List<Data>> getAllData() {
        List<Data> dataList = dataRepository.findAll();
        return ResponseEntity.ok(dataList);
    }

    /**
     * Retrieves a single Data entity by its ID.
     *
     * @param id The ID of the Data entity to retrieve.
     * @return A ResponseEntity containing the requested Data entity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Data> getDataById(@PathVariable String id) {
        return dataRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing Data entity.
     *
     * @param id      The ID of the Data entity to update.
     * @param newData The new values to update the Data entity with.
     * @return A ResponseEntity containing the updated Data entity.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Data> updateData(@PathVariable String id, @RequestBody Data newData) {
        return dataRepository.findById(id)
                .map(data -> {
                    if (newData.getTitle() != null) {
                        data.setTitle(newData.getTitle());
                    }
                    if (newData.getDescription() != null) {
                        data.setDescription(newData.getDescription());
                    }
                    if (newData.getFilePath() != null) {
                        data.setFilePath(newData.getFilePath());
                    }
                    // Add updates for other fields as necessary
                    Data updatedData = dataRepository.save(data);
                    return ResponseEntity.ok(updatedData);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes a Data entity by its ID.
     *
     * @param id The ID of the Data entity to delete.
     * @return A ResponseEntity indicating the result of the deletion operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteData(@PathVariable String id) {
        return dataRepository.findById(id)
                .map(data -> {
                    dataRepository.delete(data);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Uploads a file and associates it with a Data entity.
     *
     * @param id   The ID of the Data entity to associate the uploaded file with.
     * @param file The file to upload.
     * @return A ResponseEntity indicating the result of the upload operation.
     */
    @PostMapping("/upload/{id}")
    public ResponseEntity<?> uploadFile(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        return dataRepository.findById(id)
                .map(data -> {
                    try {
                        dataOperationService.uploadFile(file, data);
                        return ResponseEntity.ok("File uploaded successfully");
                    } catch (IOException e) {
                        return ResponseEntity.internalServerError().body("Failed to upload file");
                    }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Downloads the file associated with a Data entity.
     *
     * @param id The ID of the Data entity whose file is to be downloaded.
     * @return A ResponseEntity containing the file as a Resource.
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
        try {
            return dataOperationService.downloadFile(id);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

package ca.gov.opendata.portal.controller;


import ca.gov.opendata.portal.model.Data;
import ca.gov.opendata.portal.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {

    private final DataRepository dataRepository;

    @Autowired
    public DataController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    // Create a new dataset
    @PostMapping
    public ResponseEntity<Data> createData(@RequestBody Data data) {
        Data savedData = dataRepository.save(data);
        return ResponseEntity.ok(savedData);
    }

    // Get all datasets
    @GetMapping
    public ResponseEntity<List<Data>> getAllData() {
        List<Data> dataList = dataRepository.findAll();
        return ResponseEntity.ok(dataList);
    }

    // Get a single dataset by ID
    @GetMapping("/{id}")
    public ResponseEntity<Data> getDataById(@PathVariable String id) {
        return dataRepository.findById(id)
                .map(data -> ResponseEntity.ok(data))
                .orElse(ResponseEntity.notFound().build());
    }

    // Update a dataset
    @PutMapping("/{id}")
    public ResponseEntity<Data> updateData(@PathVariable String id, @RequestBody Data newData) {
        return dataRepository.findById(id)
                .map(data -> {
                    data.setTitle(newData.getTitle());
                    data.setDescription(newData.getDescription());
                    data.setFilePath(newData.getFilePath());
                    data.setOwnerId(newData.getOwnerId());
                    Data updatedData = dataRepository.save(data);
                    return ResponseEntity.ok(updatedData);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete a dataset
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteData(@PathVariable String id) {
        return dataRepository.findById(id)
                .map(data -> {
                    dataRepository.delete(data);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}


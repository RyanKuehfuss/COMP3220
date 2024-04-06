package ca.gov.opendata.portal.controller;

import ca.gov.opendata.portal.model.Data;
import ca.gov.opendata.portal.service.DataOperationService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for managing web interactions and operations on {@link Data} entities.
 * This controller handles web requests for listing, creating, updating, viewing,
 * deleting Data entities, and managing file uploads and downloads associated with them.
 */
@Controller
@RequestMapping("/web/data")
public class WebDataController {

    private final DataOperationService dataOperationService;

    /**
     * Constructs a WebDataController with the necessary data operation service.
     *
     * @param dataOperationService The service for data operation and file handling.
     */
    @Autowired
    public WebDataController(DataOperationService dataOperationService) {
        this.dataOperationService = dataOperationService;
    }

    /**
     * Displays a list of all Data entities.
     *
     * @param model The model to which data list is added.
     * @return The name of the HTML template displaying the data list.
     */
    @GetMapping("/list")
    public String listData(Model model) {
        model.addAttribute("dataList", dataOperationService.listAllData());
        return "data-list";
    }

    /**
     * Shows a form for creating a new Data entity.
     *
     * @param model The model to which a new Data object is added for form binding.
     * @return The name of the HTML template displaying the new data form.
     */
    @GetMapping("/new")
    public String showNewDataForm(Model model) {
        model.addAttribute("data", new Data()); 
        return "data-form";
    }

    /**
     * Processes the creation of a new Data entity.
     *
     * @param data The Data entity to be created.
     * @param redirectAttributes Attributes for a redirect scenario.
     * @return A redirection path to the data list view.
     */
    @PostMapping("/create")
    public String createData(@ModelAttribute Data data, RedirectAttributes redirectAttributes) {
        dataOperationService.saveOrUpdateData(data);
        redirectAttributes.addFlashAttribute("success", "Data created successfully.");
        return "redirect:/web/data/list";
    }

    /**
     * Displays a form for editing an existing Data entity.
     *
     * @param id    The ID of the Data entity to edit.
     * @param model The model to which the Data object is added for form binding.
     * @return The name of the HTML template displaying the edit data form.
     */
    @GetMapping("/edit/{id}")
    public String editDataForm(@PathVariable String id, Model model) {
        Data data = dataOperationService.findDataById(id);
        model.addAttribute("data", data);
        return "data-form";
    }

    /**
     * Processes the update of an existing Data entity.
     *
     * @param id                 The ID of the Data entity to update.
     * @param data               The updated Data entity.
     * @param redirectAttributes Attributes for a redirect scenario.
     * @return A redirection path to the data list view.
     */
    @PostMapping("/update/{id}")
    public String updateData(@PathVariable String id, @ModelAttribute Data data, RedirectAttributes redirectAttributes) {
        data.setId(id);
        dataOperationService.saveOrUpdateData(data);
        redirectAttributes.addFlashAttribute("success", "Data updated successfully.");
        return "redirect:/web/data/list";
    }

    /**
     * Processes the deletion of an existing Data entity.
     *
     * @param id                 The ID of the Data entity to delete.
     * @param redirectAttributes Attributes for a redirect scenario.
     * @return A redirection path to the data list view.
     */
    @GetMapping("/delete/{id}")
    public String deleteData(@PathVariable String id, RedirectAttributes redirectAttributes) {
        Data data = dataOperationService.findDataById(id);
        try {
            dataOperationService.deleteData(data);
            redirectAttributes.addFlashAttribute("success", "Data and associated file (if any) deleted successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete data: " + e.getMessage());
        }
        return "redirect:/web/data/list";
    }

    /**
     * Displays the details of a Data entity, including the content of its associated file.
     *
     * @param id    The ID of the Data entity to view.
     * @param model The model to which the Data object and file content are added.
     * @return The name of the HTML template displaying the data details.
     */
    @GetMapping("/view/{id}")
    public String viewData(@PathVariable String id, Model model) {
        Data data = dataOperationService.findDataById(id);
        model.addAttribute("data", data);
        if (data.getFilePath() != null && !data.getFilePath().isEmpty()) {
            try {
                String fileContent = dataOperationService.readFileContent(data.getFilePath());
                model.addAttribute("fileContent", fileContent);
            } catch (RuntimeException e) {
                model.addAttribute("fileContentError", "Error loading file content.");
            }
        }
        return "data-details";
    }

    /**
     * Handles the upload of a file and associates it with a Data entity.
     *
     * @param file               The file to upload.
     * @param data               The Data entity with which the file will be associated.
     * @param redirectAttributes Attributes for a redirect scenario.
     * @return A redirection path to the data list view, along with a success or error message.
     */
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @ModelAttribute Data data, RedirectAttributes redirectAttributes) {
        try {
            dataOperationService.uploadFile(file, data);
            redirectAttributes.addFlashAttribute("success", "File uploaded and data saved successfully.");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload file: " + e.getMessage());
        }
        return "redirect:/web/data/list";
    }

    /**
     * Facilitates the download of a file associated with a Data entity.
     *
     * @param id The ID of the Data entity whose file is to be downloaded.
     * @return A ResponseEntity containing the file as a Resource, or an error status if the file cannot be found or read.
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

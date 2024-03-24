package ca.gov.opendata.portal.controller;

import ca.gov.opendata.portal.model.Data;
import ca.gov.opendata.portal.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final DataRepository dataRepository;

    @Autowired
    public WebController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @GetMapping("/")
    public String listData(Model model) {
        model.addAttribute("dataList", dataRepository.findAll());
        return "data-list"; // Thymeleaf template for displaying the list
    }

    @GetMapping("/data/new")
    public String newDataForm(Model model) {
        model.addAttribute("data", new Data());
        return "data-form"; // Thymeleaf template for new data form
    }

    @PostMapping("/data/new")
    public String createData(@ModelAttribute Data data, Model model) {
        dataRepository.save(data);
        return "redirect:/"; // Redirect to the home page or the data list page
    }

    @GetMapping("/data/edit/{id}")
    public String editDataForm(@PathVariable String id, Model model) {
        Data data = dataRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid data Id:" + id));
        model.addAttribute("data", data);
        return "data-form"; // Use the same form for editing
    }

    @PostMapping("/data/edit/{id}")
    public String updateData(@PathVariable String id, @ModelAttribute Data data) {
        // Logic to update data
        dataRepository.save(data); // Ensure this method updates the data if it already exists
        return "redirect:/"; // Redirect to the data list page
    }

    @GetMapping("/data/delete/{id}")
    public String deleteData(@PathVariable String id) {
        Data data = dataRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid data Id:" + id));
        dataRepository.delete(data);
        return "redirect:/"; // Redirect to the data list page
    }
}


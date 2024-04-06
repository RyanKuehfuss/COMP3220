package ca.gov.opendata.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ca.gov.opendata.portal.repository.DataRepository;

/**
 * Controller class for handling data search functionality.
 */
@Controller
public class SearchController {

    private final DataRepository dataRepository;

    /**
     * Constructor for SearchController.
     * @param dataRepository The DataRepository for database operations.
     */
    public SearchController(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    /**
     * Display the search form.
     * @return The name of the HTML file containing the search form.
     */
    @GetMapping("/web/data/searchform")
    public String showSearchForm() {
        return "search-form";
    }

    /**
     * Process data search based on provided criteria.
     * @param title The title of the data to search for.
     * @param ownerId The ID of the owner to search for.
     * @param description The description of the data to search for.
     * @param model The model to add search results to.
     * @return The name of the HTML file containing the search results.
     */
    @PostMapping("/web/data/search")
    public String searchData(@RequestParam(required = false) String title,
                             @RequestParam(required = false) String ownerId,
                             @RequestParam(required = false) String description,
                             Model model) {
        if (title != null && !title.isEmpty()) {
            // Search by title using regex
            model.addAttribute("dataList", dataRepository.findByTitleRegex(title));
        } else if (ownerId != null && !ownerId.isEmpty()) {
            // Search by owner ID
            model.addAttribute("dataList", dataRepository.findByOwnerId(ownerId));
        } else if (description != null && !description.isEmpty()) {
            // Search by description
            model.addAttribute("dataList", dataRepository.findByDescriptionContainingIgnoreCase(description));
        } else {
            // If no search criteria provided, return all data
            model.addAttribute("dataList", dataRepository.findAll());
        }

        return "search-results";
    }
}

    

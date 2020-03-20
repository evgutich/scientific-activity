package by.it.academy.scientificactivity.controller;

import by.it.academy.scientificactivity.model.Publication;
import by.it.academy.scientificactivity.service.PublicationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/publication")
public class PublicationsController {

    final
    PublicationService publicationService;

    public PublicationsController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @GetMapping
    public String publicationList(Model model){
        List<Publication> allEmployees = publicationService.getAllPublications();
        model.addAttribute("publications", allEmployees);
        return "publications";
    }

}

package by.it.academy.scientificactivity.controller;

import by.it.academy.scientificactivity.dto.CreateEditMonographRequest;
import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.model.Monograph;
import by.it.academy.scientificactivity.model.Publication;
import by.it.academy.scientificactivity.service.EmployeeService;
import by.it.academy.scientificactivity.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("employees/{employeeId}/publications")
@Slf4j
public class PublicationsController {

    final PublicationService publicationService;

    final EmployeeService employeeService;

    public PublicationsController(PublicationService publicationService, EmployeeService employeeService) {
        this.publicationService = publicationService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String publicationList(Model model) {
        List<Publication> allEmployees = publicationService.getAllPublications();
        model.addAttribute("publications", allEmployees);
        return "publications";
    }

    @GetMapping("/monographs/{id}/edit")
    public String viewMonographEditForm(@PathVariable Long employeeId, @PathVariable("id") Long monographId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("monograph", publicationService.getPublicationById(monographId));
        return "edit-monograph";
    }

    @PostMapping(value = "/monographs/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createPublication(@PathVariable Long employeeId, @PathVariable("id") Long monographId, @ModelAttribute CreateEditMonographRequest request, Model model) {
        publicationService.updateMonographForEmployee(employeeId, monographId, request);
        Employee employeeById = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employeeById);
        model.addAttribute("monographs", publicationService.getMonographs());
        model.addAttribute("articles", publicationService.getArticles());
        return "redirect:/employees/" + employeeById.getId();
    }

}

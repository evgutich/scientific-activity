package by.it.academy.scientificactivity.controller;

import by.it.academy.scientificactivity.model.Article;
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
        List<Publication> allPublications = publicationService.getAllPublications();
        model.addAttribute("publications", allPublications);
        return "publications";
    }

    @GetMapping("monographs/add")
    public String addMonograph(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("monograph", new Monograph());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "add-monograph";
    }

    @PostMapping("monographs/save")
    public String saveMonograph(@PathVariable Long employeeId, @ModelAttribute Monograph monograph) {
        publicationService.createPublication(monograph);
        return "redirect:/employees/" + employeeId;

    }

    @GetMapping("/monographs/{monographId}/edit")
    public String viewMonographEditForm(@PathVariable Long employeeId, @PathVariable Long monographId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("monograph", publicationService.getPublicationById(monographId));
        return "edit-monograph";
    }

    @GetMapping("/monographs/{monographId}/delete")
    public String deleteMonograph(@PathVariable Long employeeId, @PathVariable Long monographId) {
        publicationService.deletePublication(monographId);
        return "redirect:/employees/" + employeeId;
    }

    @PostMapping(value = "/monographs/{monographId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editMonograph(@PathVariable Long employeeId,
                                @PathVariable Long monographId,
                                @ModelAttribute Monograph monograph,
                                Model model) {
        publicationService.updateMonographForEmployee(employeeId, monographId, monograph);
        Employee employeeById = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employeeById);
        model.addAttribute("monographs", publicationService.getMonographs());
//        model.addAttribute("articles", publicationService.getArticles());
        return "redirect:/employees/" + employeeById.getId();
    }

    @GetMapping("articles/add")
    public String addArticle(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("article", new Article());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "add-article";
    }

    @PostMapping("articles/save")
    public String saveArticle(@PathVariable Long employeeId, @ModelAttribute Article article) {
        publicationService.createPublication(article);
        return "redirect:/employees/" + employeeId;

    }

    @GetMapping("/articles/{articleId}/edit")
    public String viewArticleEditForm(@PathVariable Long employeeId, @PathVariable Long articleId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("article", publicationService.getPublicationById(articleId));
        return "edit-article";
    }

    @GetMapping("/articles/{articleId}/delete")
    public String deleteArticle(@PathVariable Long employeeId, @PathVariable Long articleId) {
        publicationService.deletePublication(articleId);
        return "redirect:/employees/" + employeeId;
    }

    @PostMapping(value = "/articles/{articleId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editArticle(@PathVariable Long employeeId,
                                @PathVariable Long articleId,
                                @ModelAttribute Article article,
                                Model model) {
        publicationService.updateArticleForEmployee(employeeId, articleId, article);
        Employee employeeById = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employeeById);
        model.addAttribute("articles", publicationService.getArticles());
        return "redirect:/employees/" + employeeById.getId();
    }

}

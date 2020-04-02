package by.it.academy.scientificactivity.controller;

import by.it.academy.scientificactivity.model.*;
import by.it.academy.scientificactivity.service.EmployeeService;
import by.it.academy.scientificactivity.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
        model.addAttribute("publications", publicationService.getAllPublications());
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
    public String saveMonograph(@PathVariable Long employeeId, @Valid @ModelAttribute Monograph monograph, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "add-monograph";
        }
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
    public String saveArticle(@PathVariable Long employeeId, @Valid @ModelAttribute Article article, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "add-article";
        }
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

    @GetMapping("textbooks/add")
    public String addTextbook(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("textbook", new Textbook());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "add-textbook";
    }

    @PostMapping("textbooks/save")
    public String saveTextbook(@PathVariable Long employeeId, @Valid @ModelAttribute Textbook textbook, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "add-textbook";
        }
        publicationService.createPublication(textbook);
        return "redirect:/employees/" + employeeId;

    }

    @GetMapping("/textbooks/{textbookId}/edit")
    public String viewTextbookEditForm(@PathVariable Long employeeId, @PathVariable Long textbookId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("textbook", publicationService.getPublicationById(textbookId));
        return "edit-textbook";
    }

    @GetMapping("/textbooks/{textbookId}/delete")
    public String deleteTextbook(@PathVariable Long employeeId, @PathVariable Long textbookId) {
        publicationService.deletePublication(textbookId);
        return "redirect:/employees/" + employeeId;
    }

    @PostMapping(value = "/textbooks/{textbookId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editTextbook(@PathVariable Long employeeId,
                               @PathVariable Long textbookId,
                               @ModelAttribute Textbook textbook,
                               Model model) {
        publicationService.updateTextbookForEmployee(employeeId, textbookId, textbook);
        Employee employeeById = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employeeById);
        model.addAttribute("textbooks", publicationService.getTextbooks());
        return "redirect:/employees/" + employeeById.getId();
    }

    @GetMapping("theses/add")
    public String addThesis(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("thesis", new Thesis());
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "add-thesis";
    }

    @PostMapping("theses/save")
    public String saveThesis(@PathVariable Long employeeId, @Valid @ModelAttribute Thesis thesis, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
            model.addAttribute("employees", employeeService.getAllEmployees());
            return "add-thesis";
        }
        publicationService.createPublication(thesis);
        return "redirect:/employees/" + employeeId;

    }

    @GetMapping("/theses/{thesisId}/edit")
    public String viewThesisEditForm(@PathVariable Long employeeId, @PathVariable Long thesisId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("thesis", publicationService.getPublicationById(thesisId));
        return "edit-thesis";
    }

    @GetMapping("/theses/{thesisId}/delete")
    public String deleteThesis(@PathVariable Long employeeId, @PathVariable Long thesisId) {
        publicationService.deletePublication(thesisId);
        return "redirect:/employees/" + employeeId;
    }

    @PostMapping(value = "/theses/{thesisId}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editArticle(@PathVariable Long employeeId,
                              @PathVariable Long thesisId,
                              @ModelAttribute Thesis thesis,
                              Model model) {
        publicationService.updateThesisForEmployee(employeeId, thesisId, thesis);
        Employee employeeById = employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee", employeeById);
        model.addAttribute("theses", publicationService.getTheses());
        return "redirect:/employees/" + employeeById.getId();
    }

}

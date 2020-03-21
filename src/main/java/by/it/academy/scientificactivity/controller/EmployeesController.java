package by.it.academy.scientificactivity.controller;

import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.service.EmployeeService;
import by.it.academy.scientificactivity.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/employees")
@Slf4j
public class EmployeesController {

    private final EmployeeService employeeService;

    private final PublicationService publicationService;

    public EmployeesController(EmployeeService employeeService, PublicationService publicationService) {
        this.employeeService = employeeService;
        this.publicationService = publicationService;
    }

    @GetMapping
    public String employeeList(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);
        return "employees";
    }

    @GetMapping("/{id}")
    public String showEmployeeProfile(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("monographs", publicationService.getMonographs());
        model.addAttribute("articles", publicationService.getArticles());
        log.info("our employee: " + employeeService.getEmployeeById(id));
        return "employee";
    }

    @GetMapping("/{id}/publications/monographs")
    public String monographCreationForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "edit-monograph";
    }

}

package by.it.academy.scientificactivity.controller;

import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final EmployeeService employeeService;

    @Value("${spring.application.name}")
    String appName;

    public HomeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String homePage(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);
        return "home";
    }
}

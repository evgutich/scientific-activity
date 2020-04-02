package by.it.academy.scientificactivity.controller;

import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.model.User;
import by.it.academy.scientificactivity.repository.EmployeeRepository;
import by.it.academy.scientificactivity.repository.UserRepository;
import by.it.academy.scientificactivity.service.EmployeeService;
import by.it.academy.scientificactivity.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final EmployeeService employeeService;

    private final PublicationService publicationService;

    final UserRepository userRepository;

    final EmployeeRepository employeeRepository;

    @Value("${spring.application.name}")
    String appName;

    public HomeController(EmployeeService employeeService, PublicationService publicationService, UserRepository userRepository, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.publicationService = publicationService;
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public String homePage(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);
        return "home";
    }

    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);
        return "welcome";
    }

//    @GetMapping("/test")
//    public String testPage(Model model) {
//        List<Employee> allEmployees = employeeService.getAllEmployees();
//        model.addAttribute("employees", allEmployees);
//        return "test";
//    }

    @GetMapping("/publications")
    public String publicationsPage(Model model) {
        model.addAttribute("publications", publicationService.getAllPublications());
        return "publications";
    }

    @GetMapping("/publications/{publicationId}/delete")
    public String publicationDelete(@PathVariable Long publicationId) {
        publicationService.deletePublication(publicationId);
        return "redirect:/publications";
    }

    @GetMapping("/403")
    public String forbiddenPage() {
        return "403";
    }

    @GetMapping("/myPage")
    public String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(authentication.getName());
        Employee employee = employeeRepository.findByUser(user);
        return "redirect:/employees/" + employee.getId();
    }
}

package by.it.academy.scientificactivity.controller;

import by.it.academy.scientificactivity.model.Department;
import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.service.DepartmentService;
import by.it.academy.scientificactivity.service.EmployeeService;
import by.it.academy.scientificactivity.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/employees")
@Slf4j
public class EmployeesController {

    private final EmployeeService employeeService;

    private final PublicationService publicationService;

    private final DepartmentService departmentService;

    public EmployeesController(EmployeeService employeeService, PublicationService publicationService, DepartmentService departmentService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeService = employeeService;
        this.publicationService = publicationService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String employeeList(Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);
        return "employees";
    }

    @RequestMapping("add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "add-employee";
    }

    @PostMapping("save")
    public String save(@ModelAttribute Department department, @Valid Employee employee, BindingResult bindingResult, Model model) {
        if (!isUserValid(employee, bindingResult)) {
            model.addAttribute("departments", departmentService.getAllDepartments());
            return "add-employee";
        }
        employeeService.createEmployee(employee, department.getDepartmentId());
        return "redirect:/employees";
    }

    private boolean isUserValid(Employee employee, BindingResult bindingResult) {
        if (!employeeService.isUniqueEmployee(employee)) {
            bindingResult.rejectValue("user.userName", "user.userName", "This username is already registered");
        }
        if (bindingResult.hasErrors()) {
            return false;
        }
        return true;
    }

    @GetMapping("/{employeeId}/edit")
    public String viewEmployeeEditForm(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("departments", departmentService.getAllDepartments());
        return "edit-employee";
    }

    @GetMapping("/{employeeId}/delete")
    public String deleteEmployee(@PathVariable Long employeeId, Model model) {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/employees";
    }

    @PostMapping(value = "/{employeeId}/update", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String updateEmployee(@PathVariable Long employeeId, @ModelAttribute Employee employee, @ModelAttribute Department department) {
        employeeService.updateEmployee(employeeId, employee, department.getDepartmentId());
        return "redirect:/employees";
    }

    @GetMapping("/{employeeId}")
    public String showEmployeeProfile(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("monographs", publicationService.getMonographsByAuthorId(employeeId));
        model.addAttribute("articles", publicationService.getArticlesByAuthorId(employeeId));
        model.addAttribute("textbooks", publicationService.getTextbooksByAuthorId(employeeId));
        model.addAttribute("theses", publicationService.getThesesByAuthorId(employeeId));
        log.info("our employee: " + employeeService.getEmployeeById(employeeId));
        return "employee";
    }

}

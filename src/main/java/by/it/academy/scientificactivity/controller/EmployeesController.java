package by.it.academy.scientificactivity.controller;

import by.it.academy.scientificactivity.model.Department;
import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.service.DepartmentService;
import by.it.academy.scientificactivity.service.EmployeeService;
import by.it.academy.scientificactivity.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/employees")
@Slf4j
public class EmployeesController {

    private final EmployeeService employeeService;

    private final PublicationService publicationService;

    private final DepartmentService departmentService;

    public EmployeesController(EmployeeService employeeService, PublicationService publicationService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.publicationService = publicationService;
        this.departmentService = departmentService;
    }

    @GetMapping
    public String employeeList(@ModelAttribute Department department, Model model) {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);
        model.addAttribute("department", department.getDepartmentName());
        return "employees";
    }

    @RequestMapping(value = "add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
//        model.addAttribute("departments", departmentService.getAllDepartments());
        return "add-employee";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute Department department, Employee employee) {
        employeeService.createEmployee(employee, department.getDepartmentId());
        return "redirect:/employees";
    }

    @RequestMapping(value = "/{employeeId}/delete", method = RequestMethod.GET)
    public String editRemoveEmployee(@PathVariable Long employeeId, Model model) {
        employeeService.deleteEmployee(employeeId);
        return "redirect:/employees";
    }

    @GetMapping("/{employeeId}")
    public String showEmployeeProfile(@PathVariable Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(employeeId));
        model.addAttribute("monographs", publicationService.getMonographsByAuthorId(employeeId));
        model.addAttribute("articles", publicationService.getArticles());
        log.info("our employee: " + employeeService.getEmployeeById(employeeId));
        return "employee";
    }

    @GetMapping("/{id}/publications/monographs")
    public String monographCreationForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "edit-monograph";
    }

}

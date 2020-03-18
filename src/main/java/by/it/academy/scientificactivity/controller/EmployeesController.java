package by.it.academy.scientificactivity.controller;

import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class EmployeesController {

    @Autowired
    private EmployeeService employeeService;

    public String employeeList(Model model){
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);
        return "employees";
    }
}

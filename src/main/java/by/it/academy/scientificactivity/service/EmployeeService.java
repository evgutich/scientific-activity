package by.it.academy.scientificactivity.service;

import by.it.academy.scientificactivity.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    void deleteEmployee(Long id);

    Employee createEmployee(Employee employee);

    Employee updateEmployee(Employee employee);
}

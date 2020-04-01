package by.it.academy.scientificactivity.service;

import by.it.academy.scientificactivity.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee getEmployeeByUserName(String userName);

    void deleteEmployee(Long id);

    Employee createEmployee(Employee employee, Long departmentId);

    Employee updateEmployee(Long employeeId, Employee employee, Long departmentId);
}

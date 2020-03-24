package by.it.academy.scientificactivity.service.impl;

import by.it.academy.scientificactivity.exception.DepartmentNotFoundException;
import by.it.academy.scientificactivity.exception.EmployeeNotFoundException;
import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.repository.DepartmentRepository;
import by.it.academy.scientificactivity.repository.EmployeeRepository;
import by.it.academy.scientificactivity.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee createEmployee(Employee employee, Long departmentId) {
        employee.setDepartment(departmentRepository.findById(departmentId).orElseThrow(DepartmentNotFoundException::new));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}

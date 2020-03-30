package by.it.academy.scientificactivity.service.impl;

import by.it.academy.scientificactivity.exception.DepartmentNotFoundException;
import by.it.academy.scientificactivity.exception.EmployeeNotFoundException;
import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.model.Publication;
import by.it.academy.scientificactivity.repository.DepartmentRepository;
import by.it.academy.scientificactivity.repository.EmployeeRepository;
import by.it.academy.scientificactivity.repository.PublicationRepository;
import by.it.academy.scientificactivity.service.EmployeeService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final PublicationRepository publicationRepository;

    private final DepartmentRepository departmentRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               PublicationRepository publicationRepository,
                               DepartmentRepository departmentRepository,
                               @Lazy
                                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.publicationRepository = publicationRepository;
        this.departmentRepository = departmentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public Employee getEmployeeByUserName(String userName) {
        return employeeRepository.findEmployeeByUserName(userName);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        for (Publication publication : employee.getPublications()) {
            if (publication.getAuthors().size() != 1) {
                publication.removeAuthor(employee);
            } else publicationRepository.delete(publication);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee createEmployee(Employee employee, Long departmentId) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        employee.setActive(true);
        employee.setDepartment(departmentRepository.findById(departmentId).orElseThrow(DepartmentNotFoundException::new));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }
}

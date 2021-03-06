package by.it.academy.scientificactivity.service.impl;

import by.it.academy.scientificactivity.exception.DepartmentNotFoundException;
import by.it.academy.scientificactivity.exception.EmployeeNotFoundException;
import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.model.Publication;
import by.it.academy.scientificactivity.repository.*;
import by.it.academy.scientificactivity.service.EmployeeService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static by.it.academy.scientificactivity.model.Role.ROLE_USER;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final UserRepository userRepository;

    private final PublicationRepository publicationRepository;

    private final DepartmentRepository departmentRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RoleRepository roleRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               PublicationRepository publicationRepository,
                               DepartmentRepository departmentRepository,
                               @Lazy
                                       BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.publicationRepository = publicationRepository;
        this.departmentRepository = departmentRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
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
        employee.getUser().setPassword(bCryptPasswordEncoder.encode(employee.getUser().getPassword()));
        employee.getUser().setActive(true);
        employee.getUser().setRoles(new HashSet<>(Arrays.asList(roleRepository.findEmployeeRoleByRole(ROLE_USER))));
        employee.setDepartment(departmentRepository.findById(departmentId).orElseThrow(DepartmentNotFoundException::new));
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee employee, Long departmentId) {
        Employee newEmployee = employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
        newEmployee.setSurname(employee.getSurname());
        newEmployee.setName(employee.getName());
        newEmployee.setPatronymic(employee.getPatronymic());
        newEmployee.setDateOfBirth(employee.getDateOfBirth());
        newEmployee.setDepartment(departmentRepository.findById(departmentId).orElseThrow(DepartmentNotFoundException::new));
        newEmployee.setPosition(employee.getPosition());
        newEmployee.setDegree(employee.getDegree());
        newEmployee.setAcademicRank(employee.getAcademicRank());
        return employeeRepository.save(newEmployee);
    }

    @Override
    public boolean isUniqueEmployee(Employee employee) {
        return userRepository.findByUserName(employee.getUser().getUserName()) == null;
    }
}

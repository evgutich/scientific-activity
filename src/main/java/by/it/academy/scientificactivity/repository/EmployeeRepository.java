package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findAll();
    Employee findEmployeeByUserName(String userName);
}

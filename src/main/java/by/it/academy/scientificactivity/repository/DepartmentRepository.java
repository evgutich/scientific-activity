package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findAll();
}

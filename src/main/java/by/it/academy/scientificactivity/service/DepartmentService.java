package by.it.academy.scientificactivity.service;

import by.it.academy.scientificactivity.model.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    void deleteDepartment(Long id);

    Department createDepartment(Department department);

    Department updateDepartment(Department department);
}

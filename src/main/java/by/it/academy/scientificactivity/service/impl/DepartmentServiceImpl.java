package by.it.academy.scientificactivity.service.impl;

import by.it.academy.scientificactivity.exception.DepartmentNotFoundException;
import by.it.academy.scientificactivity.model.Department;
import by.it.academy.scientificactivity.repository.DepartmentRepository;
import by.it.academy.scientificactivity.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    final
    DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(DepartmentNotFoundException::new);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }
}

package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.EmployeeRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<EmployeeRole, Long> {
    EmployeeRole findEmployeeRoleByRole(String role);
}

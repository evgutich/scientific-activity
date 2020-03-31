package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.EmployeeRole;
import by.it.academy.scientificactivity.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<EmployeeRole, Long> {
    EmployeeRole findEmployeeRoleByRole(Role role);
}

package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.UserRole;
import by.it.academy.scientificactivity.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole, Long> {
    UserRole findEmployeeRoleByRole(Role role);
}

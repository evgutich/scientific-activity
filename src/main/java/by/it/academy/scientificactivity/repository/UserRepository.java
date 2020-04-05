package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}

package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.Thesis;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ThesisRepository extends CrudRepository<Thesis, Long> {
    List<Thesis> findAll();
}

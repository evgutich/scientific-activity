package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.Monograph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MonographRepository extends CrudRepository<Monograph, Long> {

    List<Monograph> findAll();
//    List<Monograph> findMonographsByAuthorId(Long id);
}

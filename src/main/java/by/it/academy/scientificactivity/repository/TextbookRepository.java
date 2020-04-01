package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.Textbook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TextbookRepository extends CrudRepository<Textbook, Long> {
    List<Textbook> findAll();
}

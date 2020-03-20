package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.Publication;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublicationRepository extends CrudRepository<Publication, Long> {
    List<Publication> findAll();
}

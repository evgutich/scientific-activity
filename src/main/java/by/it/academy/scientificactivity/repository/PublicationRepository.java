package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.Publication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublicationRepository extends CrudRepository<Publication, Long> {

    List<Publication> findAll();

    @Query("SELECT p FROM Publication p WHERE TYPE(p) = Monograph")
    List<Publication> findMonographs();

    @Query("SELECT p FROM Publication p WHERE TYPE(p) = Article")
    List<Publication> findArticles();

    @Query("SELECT p FROM Publication p join p.authors a WHERE a.id = :id and TYPE(p) = Monograph")
    List<Publication> findMonographByAuthorId(Long id);
}

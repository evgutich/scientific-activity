package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.Publication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PublicationRepository extends CrudRepository<Publication, Long> {

    List<Publication> findAll();

    @Query("SELECT p FROM Publication p join p.authors a WHERE a.id = :id and TYPE(p) = Monograph")
    List<Publication> findMonographByAuthorId(Long id);

    @Query("SELECT p FROM Publication p join p.authors a WHERE a.id = :id and TYPE(p) = Article")
    List<Publication> findArticleByAuthorId(Long id);

    @Query("SELECT p FROM Publication p join p.authors a WHERE a.id = :id and TYPE(p) = Textbook")
    List<Publication> findTextbookByAuthorId(Long id);

    @Query("SELECT p FROM Publication p join p.authors a WHERE a.id = :id and TYPE(p) = Thesis")
    List<Publication> findThesisByAuthorId(Long id);

    List<Publication> findAllByOrderByEntryDateAsc();

    List<Publication> findAllByOrderByEntryDateDesc();
}

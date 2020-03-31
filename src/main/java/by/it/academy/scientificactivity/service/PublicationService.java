package by.it.academy.scientificactivity.service;

import by.it.academy.scientificactivity.model.Monograph;
import by.it.academy.scientificactivity.model.Publication;

import java.util.List;

public interface PublicationService {

    List<Publication> getAllPublications();

    Publication getPublicationById(Long id);

    void deletePublication(Long id);

    Publication createPublication(Publication publication);

    Publication updatePublication(Publication publication);

    List<Monograph> getMonographs();

    List<Publication> getMonographsByAuthorId(Long id);

    List<Publication> getArticles();

    void updateMonographForEmployee(Long employeeId, Long monographId, Monograph monograph);

    List<Publication> getAllByOrderByEntryDateAsc();

    List<Publication> getAllByOrderByEntryDateDesc();

}

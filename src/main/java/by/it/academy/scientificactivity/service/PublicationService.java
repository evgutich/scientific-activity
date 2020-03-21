package by.it.academy.scientificactivity.service;

import by.it.academy.scientificactivity.dto.CreateEditMonographRequest;
import by.it.academy.scientificactivity.model.Monograph;
import by.it.academy.scientificactivity.model.Publication;

import java.util.List;

public interface PublicationService {

    List<Publication> getAllPublications();

    Publication getPublicationById(Long id);

    void deletePublication(Long id);

    Publication createPublication(Publication publication);

    Publication updatePublication(Publication publication);

    List<Publication> getMonographs();

    List<Publication> getMonographsByAuthorId(Long id);

    List<Publication> getArticles();

    void updateMonographForEmployee(Long employeeId, Long monographId, CreateEditMonographRequest request);

}

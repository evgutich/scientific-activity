package by.it.academy.scientificactivity.service;

import by.it.academy.scientificactivity.model.Publication;

import java.util.List;
import java.util.Optional;

public interface PublicationService {

    List<Publication> getAllPublications();

    Optional<Publication> getPublicationById(Long id);

    void deletePublication(Long id);

    Publication createPublication(Publication publication);

    Publication updatePublication(Publication publication);
}

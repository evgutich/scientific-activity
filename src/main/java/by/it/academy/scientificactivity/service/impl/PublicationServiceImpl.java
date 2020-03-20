package by.it.academy.scientificactivity.service.impl;

import by.it.academy.scientificactivity.model.Publication;
import by.it.academy.scientificactivity.repository.PublicationRepository;
import by.it.academy.scientificactivity.service.PublicationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationService {

    final
    PublicationRepository publicationRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    @Override
    public Optional<Publication> getPublicationById(Long id) {
        return publicationRepository.findById(id);
    }

    @Override
    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    @Override
    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    @Override
    public Publication updatePublication(Publication publication) {
        return publicationRepository.save(publication);
    }
}

package by.it.academy.scientificactivity.service.impl;

import by.it.academy.scientificactivity.dto.CreateEditMonographRequest;
import by.it.academy.scientificactivity.exception.PublicationNotFoundException;
import by.it.academy.scientificactivity.model.Publication;
import by.it.academy.scientificactivity.repository.PublicationRepository;
import by.it.academy.scientificactivity.service.PublicationService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Publication getPublicationById(Long id) {
        return publicationRepository.findById(id).orElseThrow(PublicationNotFoundException::new);
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

    @Override
    public List<Publication> getMonographs() {
        return publicationRepository.findMonographs();
    }

    @Override
    public List<Publication> getMonographsByAuthorId(Long id) {
        return publicationRepository.findMonographByAuthorId(id);
    }

    @Override
    public List<Publication> getArticles() {
        return publicationRepository.findArticles();
    }

    @Override
    public void updateMonographForEmployee(Long employeeId, Long monographId, CreateEditMonographRequest request) {
        Publication monograph = publicationRepository.findById(monographId).orElseThrow(PublicationNotFoundException::new);
        monograph.setTitle(request.getTitle());
        publicationRepository.save(monograph);
    }
}

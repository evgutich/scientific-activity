package by.it.academy.scientificactivity.service.impl;

import by.it.academy.scientificactivity.exception.EmployeeNotFoundException;
import by.it.academy.scientificactivity.exception.PublicationNotFoundException;
import by.it.academy.scientificactivity.model.Employee;
import by.it.academy.scientificactivity.model.Monograph;
import by.it.academy.scientificactivity.model.Publication;
import by.it.academy.scientificactivity.repository.EmployeeRepository;
import by.it.academy.scientificactivity.repository.MonographRepository;
import by.it.academy.scientificactivity.repository.PublicationRepository;
import by.it.academy.scientificactivity.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional
public class PublicationServiceImpl implements PublicationService {

    final PublicationRepository publicationRepository;

    final MonographRepository monographRepository;

    final EmployeeRepository employeeRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository, EmployeeRepository employeeRepository, MonographRepository monographRepository) {
        this.publicationRepository = publicationRepository;
        this.employeeRepository = employeeRepository;
        this.monographRepository = monographRepository;
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
        Publication publication = publicationRepository.findById(id).orElseThrow(PublicationNotFoundException::new);
        for (Employee author : publication.getAuthors()) {
            author.removePublication(publication);
        }
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
    public List<Monograph> getMonographs() {
//        return publicationRepository.findMonographs();
        return monographRepository.findAll();
    }

    @Override
    public List<Publication> getMonographsByAuthorId(Long id) {
        return publicationRepository.findMonographByAuthorId(id);
//        return monographRepository.findMonographsByAuthorId(id);
    }

    @Override
    public List<Publication> getArticles() {
        return publicationRepository.findArticles();
    }

    @Override
    public void updateMonographForEmployee(Long employeeId, Long monographId, Monograph monograph) {
        Monograph newMonograph = monographRepository.findById(monographId).orElseThrow(PublicationNotFoundException::new);
        List<Employee> authorsList = monograph.getAuthors().stream()
                .map((auth) -> employeeRepository.findById(auth.getId()).orElseThrow(EmployeeNotFoundException::new))
                .collect(toList());
        newMonograph.setTitle(monograph.getTitle());
        newMonograph.setPublisher(monograph.getPublisher());
        newMonograph.setMonographType(monograph.getMonographType());
        newMonograph.setPrintRun(monograph.getPrintRun());
        newMonograph.setNumberOfPages(monograph.getNumberOfPages());
        newMonograph.setAuthors(authorsList);
        publicationRepository.save(newMonograph);
    }

    @Override
    public List<Publication> getAllByOrderByEntryDateAsc() {
        return publicationRepository.findAllByOrderByEntryDateAsc();
    }

    @Override
    public List<Publication> getAllByOrderByEntryDateDesc() {
        return publicationRepository.findAllByOrderByEntryDateDesc();
    }
}

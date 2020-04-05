package by.it.academy.scientificactivity.service.impl;

import by.it.academy.scientificactivity.exception.EmployeeNotFoundException;
import by.it.academy.scientificactivity.exception.PublicationNotFoundException;
import by.it.academy.scientificactivity.model.*;
import by.it.academy.scientificactivity.repository.*;
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

    final ArticleRepository articleRepository;

    final TextbookRepository textbookRepository;

    final ThesisRepository thesisRepository;

    public PublicationServiceImpl(PublicationRepository publicationRepository, EmployeeRepository employeeRepository, MonographRepository monographRepository, ArticleRepository articleRepository, TextbookRepository textbookRepository, ThesisRepository thesisRepository) {
        this.publicationRepository = publicationRepository;
        this.employeeRepository = employeeRepository;
        this.monographRepository = monographRepository;
        this.articleRepository = articleRepository;
        this.textbookRepository = textbookRepository;
        this.thesisRepository = thesisRepository;
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
        return monographRepository.findAll();
    }

    @Override
    public List<Publication> getMonographsByAuthorId(Long id) {
        return publicationRepository.findMonographByAuthorId(id);
    }

    @Override
    public List<Publication> getArticlesByAuthorId(Long id) {
        return publicationRepository.findArticleByAuthorId(id);
    }

    @Override
    public List<Publication> getTextbooksByAuthorId(Long id) {
        return publicationRepository.findTextbookByAuthorId(id);
    }

    @Override
    public List<Publication> getThesesByAuthorId(Long id) {
        return publicationRepository.findThesisByAuthorId(id);
    }

    @Override
    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    @Override
    public List<Textbook> getTextbooks() {
        return textbookRepository.findAll();
    }

    @Override
    public List<Thesis> getTheses() {
        return thesisRepository.findAll();
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
    public void updateArticleForEmployee(Long employeeId, Long articleId, Article article) {
        Article newArticle = articleRepository.findById(articleId).orElseThrow(PublicationNotFoundException::new);
        List<Employee> authorsList = article.getAuthors().stream()
                .map((auth) -> employeeRepository.findById(auth.getId()).orElseThrow(EmployeeNotFoundException::new))
                .collect(toList());
        newArticle.setTitle(article.getTitle());
        newArticle.setPublisher(article.getPublisher());
        newArticle.setArticleType(article.getArticleType());
        newArticle.setArticleEdition(article.getArticleEdition());
        newArticle.setPublicationLanguage(article.getPublicationLanguage());
        newArticle.setAuthors(authorsList);
        publicationRepository.save(newArticle);
    }

    @Override
    public void updateTextbookForEmployee(Long employeeId, Long textbookID, Textbook textbook) {
        Textbook newTextbook = textbookRepository.findById(textbookID).orElseThrow(PublicationNotFoundException::new);
        List<Employee> authorsList = textbook.getAuthors().stream()
                .map((auth) -> employeeRepository.findById(auth.getId()).orElseThrow(EmployeeNotFoundException::new))
                .collect(toList());
        newTextbook.setTitle(textbook.getTitle());
        newTextbook.setPublisher(textbook.getPublisher());
        newTextbook.setTextbookType(textbook.getTextbookType());
        newTextbook.setPrintRun(textbook.getPrintRun());
        newTextbook.setNumberOfPages(textbook.getNumberOfPages());
        newTextbook.setAuthors(authorsList);
        publicationRepository.save(newTextbook);
    }

    @Override
    public void updateThesisForEmployee(Long employeeId, Long thesisId, Thesis thesis) {
        Thesis newThesis = thesisRepository.findById(thesisId).orElseThrow(PublicationNotFoundException::new);
        List<Employee> authorsList = thesis.getAuthors().stream()
                .map((auth) -> employeeRepository.findById(auth.getId()).orElseThrow(EmployeeNotFoundException::new))
                .collect(toList());
        newThesis.setTitle(thesis.getTitle());
        newThesis.setPublisher(thesis.getPublisher());
        newThesis.setThesisType(thesis.getThesisType());
        newThesis.setPublicationLanguage(thesis.getPublicationLanguage());
        newThesis.setAuthors(authorsList);
        publicationRepository.save(newThesis);
    }
}

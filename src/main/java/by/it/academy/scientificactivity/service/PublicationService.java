package by.it.academy.scientificactivity.service;

import by.it.academy.scientificactivity.model.*;

import java.util.List;

public interface PublicationService {

    List<Publication> getAllPublications();

    Publication getPublicationById(Long id);

    void deletePublication(Long id);

    Publication createPublication(Publication publication);

    Publication updatePublication(Publication publication);

    List<Monograph> getMonographs();

    List<Article> getArticles();

    List<Textbook> getTextbooks();

    List<Thesis> getTheses();

    List<Publication> getMonographsByAuthorId(Long id);

    List<Publication> getArticlesByAuthorId(Long id);

    List<Publication> getTextbooksByAuthorId(Long id);

    List<Publication> getThesesByAuthorId(Long id);

    void updateMonographForEmployee(Long employeeId, Long monographId, Monograph monograph);

    void updateArticleForEmployee(Long employeeId, Long articleId, Article article);

    void updateTextbookForEmployee(Long employeeId, Long textbookId, Textbook textbook);

    void updateThesisForEmployee(Long employeeId, Long thesisId, Thesis thesis);

}

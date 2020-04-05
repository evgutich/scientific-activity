package by.it.academy.scientificactivity.repository;

import by.it.academy.scientificactivity.model.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    List<Article> findAll();
}

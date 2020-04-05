package by.it.academy.scientificactivity.controller.api;

import by.it.academy.scientificactivity.model.Article;
import by.it.academy.scientificactivity.service.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class PublicationController {

    final
    PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

//    @PostMapping("/monograph")
//    public void createMonograph(@RequestBody Monograph monograph) {
//        publicationService.createPublication(monograph);
//    }

    @PostMapping("/article")
    public void createArticle(@RequestBody Article article) {
        log.info("OUR PUBLICATION: " + article);
    }
}

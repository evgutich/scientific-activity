package by.it.academy.scientificactivity.model;

public enum ArticleEdition {
    RINC_SCOPUS_WEB_OF_SCIENCE("article.edition.rinc-core"),
    OTHER("article.edition.other");

    ArticleEdition(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }

    private final String propName;
}

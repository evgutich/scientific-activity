package by.it.academy.scientificactivity.model;

public enum PublicationLanguage {
    RUSSIAN_BELARUSSIAN("publication.language.ru-bel"),
    ENGLISH("publication.language.en");

    PublicationLanguage(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }

    private final String propName;
}

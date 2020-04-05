package by.it.academy.scientificactivity.model;

public enum ArticleType {
    IN_RB_VAK_PUBLICATION("article.type.in-rb-vak"),
    IN_OTHER_RB_PUBLICATION("article.type.in-other-rb"),
    IN_FOREIGN_SNG_PUBLICATION("article.type.in-sng"),
    IN_FOREIGN_PUBLICATION("article.type.in-foreign");

    ArticleType(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }

    private final String propName;
}

package by.it.academy.scientificactivity.model;

public enum TextbookType {
    TEXTBOOK("textbook.type.textbook"),
    CATALOG("textbook.type.catalog"),
    TEACHING_AID("textbook.type.teaching-aid"),
    GUIDE("textbook.type.guide"),
    VOCABULARY("textbook.type.vocabulary");

    TextbookType(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }

    private final String propName;
}

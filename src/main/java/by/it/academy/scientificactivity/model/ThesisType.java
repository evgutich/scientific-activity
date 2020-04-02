package by.it.academy.scientificactivity.model;

public enum ThesisType {
    IN_RB_PUBLICATION("thesis.type.in-rb"),
    IN_FOREIGN_SNG_PUBLICATION("thesis.type.in-sng"),
    IN_FOREIGN_PUBLICATION("thesis.type.foreign");

    ThesisType(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }

    private final String propName;
}

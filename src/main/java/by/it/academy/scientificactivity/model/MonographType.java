package by.it.academy.scientificactivity.model;

public enum MonographType {
    IN_RB("monograph.type.in-rb"),
    FOREIGN("monograph.type.foreign");

    MonographType(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }

    private final String propName;
}

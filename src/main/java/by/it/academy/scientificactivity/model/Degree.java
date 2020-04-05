package by.it.academy.scientificactivity.model;

public enum Degree {
    WITHOUT_DEGREE("degree.without-degree"),
    PHD_MEDICAL("degree.phd-medical"),
    PHD_BIOLOGICAL("degree.phd-biological"),
    PHD_TECHNICAL("degree.phd-technical"),
    MD_MEDICAL("degree.md-medical"),
    MD_BIOLOGICAL("degree.md-biological");

    Degree(String propName) {
        this.propName = propName;
    }

    public String getPropName() {
        return propName;
    }

    private final String propName;
}

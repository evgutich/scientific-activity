package by.it.academy.scientificactivity.model;


public enum AcademicRank {
    WITHOUT_RANK("rank.without-rank"),
    DOCENT("rank.docent"),
    PROFESSOR("rank.professor");

    public String getPropName() {
        return propName;
    }

    private final String propName;

    AcademicRank(String propName) {
        this.propName = propName;
    }
}

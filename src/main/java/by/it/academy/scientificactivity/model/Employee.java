package by.it.academy.scientificactivity.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String patronymic;
    private String surname;
    @Column(name = "date_of_birth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Department department;
    private String position;
    @Enumerated(EnumType.STRING)
    private Degree degree;
    @Enumerated(EnumType.STRING)
    private AcademicRank academicRank;
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "authors")
    private List<Publication> publications = new ArrayList<>();

    public void addPublication(Publication publication) {
        this.publications.add(publication);
        publication.getAuthors().add(this);
    }

    public void removePublication(Publication publication) {
        this.publications.remove(publication);
        publication.getAuthors().remove(publication);
    }
}

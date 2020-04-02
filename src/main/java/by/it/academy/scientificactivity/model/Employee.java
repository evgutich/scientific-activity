package by.it.academy.scientificactivity.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    @Length(min = 2, message = "*Your name must have at least 2 characters")
    @NotBlank(message = "*Please provide a name")
    private String name;
    @Length(min = 2, message = "*Your patronymic must have at least 2 characters")
    @NotBlank(message = "*Please provide a patronymic")
    private String patronymic;
    @Length(min = 2, message = "*Your surname must have at least 2 characters")
    @NotBlank(message = "*Please provide a surname")
    private String surname;
    @Column(name = "date_of_birth")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "*Date input is invalid for a birth date")
    @NotNull(message = "*Please provide a valid date of birth")
    private LocalDate dateOfBirth;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Department department;
    @NotEmpty(message = "*Please provide a position")
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

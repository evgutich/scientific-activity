package by.it.academy.scientificactivity.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Length(min = 5, message = "*Your user name must have at least 5 characters")
    @NotEmpty(message = "*Please provide a user name")
    private String userName;

    @Length(min = 4, message = "*Your password must have at least 4 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    private Boolean active;

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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<EmployeeRole> roles = new HashSet<>();

    public void addPublication(Publication publication) {
        this.publications.add(publication);
        publication.getAuthors().add(this);
    }

    public void removePublication(Publication publication) {
        this.publications.remove(publication);
        publication.getAuthors().remove(publication);
    }
}

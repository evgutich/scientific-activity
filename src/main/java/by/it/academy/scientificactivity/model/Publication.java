package by.it.academy.scientificactivity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Please provide title")
    private String title;
    private String publisher;
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "employee_publications",
            joinColumns = {@JoinColumn(name = "publications_id")},
            inverseJoinColumns = {@JoinColumn(name = "authors_id")})
    private List<Employee> authors = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime entryDate;

    public void addAuthor(Employee e) {
        this.authors.add(e);
        e.getPublications().add(this);
    }

    public void removeAuthor(Employee e) {
        this.authors.remove(e);
        e.getPublications().remove(e);
    }
}

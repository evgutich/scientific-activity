package by.it.academy.scientificactivity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    private String title;
    private String publisher;
    @JsonBackReference
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "publications")
    private List<Employee> authors = new ArrayList<>();
    private LocalDate entryDate;
}

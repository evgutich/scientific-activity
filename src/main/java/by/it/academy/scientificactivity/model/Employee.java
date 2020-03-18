package by.it.academy.scientificactivity.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String patronymic;
    private String surname;
    @Column(name = "date_of_birth")
//    @DateTimeFormat(iso = DateTimeFormatter)
//    @JsonFormat(pattern = "YYYY-MM-dd")
    private LocalDate dateOfBirth;
    private String department;

}

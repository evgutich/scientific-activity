package by.it.academy.scientificactivity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "publication_id")
@Entity
public class Monograph extends Publication {
    @Enumerated(EnumType.STRING)
    private MonographType monographType;
    @NotEmpty(message = "*Please provide a print run")
    private Integer printRun;
    private Integer numberOfPages;
}

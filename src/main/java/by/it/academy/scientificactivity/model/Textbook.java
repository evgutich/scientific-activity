package by.it.academy.scientificactivity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "publication_id")
@Entity
public class Textbook extends Publication {
    @Enumerated(EnumType.STRING)
    private TextbookType textbookType;
    @Min(value = 5, message = "*Print run should have at least 5 publications")
    @NotNull(message = "*Please provide a print run")
    private Integer printRun;
    @Positive(message = "*Publication should have at least one page")
    @NotNull(message = "*Please provide a number of pages")
    private Integer numberOfPages;
}

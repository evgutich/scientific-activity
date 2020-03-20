package by.it.academy.scientificactivity.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "publication_id")
@Entity
public class Textbook extends Publication{
    @Enumerated(EnumType.STRING)
    private TextbookType textbookType;
    private Integer printRun;
    private Integer numberOfPages;
}

package by.it.academy.scientificactivity.dto;

import by.it.academy.scientificactivity.model.MonographType;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateEditMonographRequest {
    private String title;
    private String publisher;
    private List<Long> authorsId = new ArrayList<>();
    private LocalDate entryDate;
    private MonographType monographType;
    private Integer printRun;
    private Integer numberOfPages;
}

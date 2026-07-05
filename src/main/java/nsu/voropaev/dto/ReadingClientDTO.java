package nsu.voropaev.dto;

import lombok.Value;
import java.time.LocalDate;

@Value
public class ReadingClientDTO {
    String fullName;
    LocalDate dateOfBirth;
    String bookName;
    String bookAuthor;
    String isbn;
    LocalDate borrowedDate;
}
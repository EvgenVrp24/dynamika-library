package nsu.voropaev.dto;

import java.time.LocalDate;

public class ReadingClientDTO {

    private String fullName;
    private LocalDate dateOfBirth;
    private String bookName;
    private String bookAuthor;
    private String isbn;
    private LocalDate borrowedDate;

    public ReadingClientDTO(String fullName, LocalDate dateOfBirth,
                            String bookName, String bookAuthor,
                            String isbn, LocalDate borrowedDate) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.isbn = isbn;
        this.borrowedDate = borrowedDate;
    }

    public String getFullName() { return fullName; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getBookName() { return bookName; }
    public String getBookAuthor() { return bookAuthor; }
    public String getIsbn() { return isbn; }
    public LocalDate getBorrowedDate() { return borrowedDate; }
}
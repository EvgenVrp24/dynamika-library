package nsu.voropaev.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название обязательно")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "Автор обязателен")
    @Size(max = 255)
    private String author;

    @NotBlank(message = "ISBN обязателен")
    //@Pattern(regexp = "^(?:\\d{9}[\\dX]|\\d{13})$", message = "Неверный формат ISBN")
    @Column(unique = true, length = 20)
    private String isbn;

    public Book() {}

    public Book(String name, String author, String isbn) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
}
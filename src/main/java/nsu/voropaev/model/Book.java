package nsu.voropaev.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
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
    @Size(min = 10, max = 13, message = "ISBN должен содержать от 10 до 13 символов после очистки")
    @Column(unique = true, length = 13)
    private String isbn;

    public Book(String name, String author, String isbn) {
        this.name = name;
        this.author = author;
        this.setIsbn(isbn);
    }

    public void setIsbn(String isbn) {
        if (isbn != null) {
            isbn = isbn.replaceAll("[^0-9Xx]", "").toUpperCase();
        }
        this.isbn = isbn;
    }
}
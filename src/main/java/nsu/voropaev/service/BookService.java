package nsu.voropaev.service;

import nsu.voropaev.model.Book;
import nsu.voropaev.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepo.findAll(pageable);
    }

    public Optional<Book> findById(Long id) {
        return bookRepo.findById(id);
    }

    public Book save(Book book) {
        return bookRepo.save(book);
    }

    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }
}

package nsu.voropaev.service;

import nsu.voropaev.dto.ReadingClientDTO;
import nsu.voropaev.model.Book;
import nsu.voropaev.model.Borrowing;
import nsu.voropaev.model.Client;
import nsu.voropaev.repository.BorrowingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class BorrowingService {

    private final BorrowingRepository borrowingRepo;
    private final ClientService clientService;
    private final BookService bookService;

    public BorrowingService(BorrowingRepository borrowingRepo,
                            ClientService clientService,
                            BookService bookService) {
        this.borrowingRepo = borrowingRepo;
        this.clientService = clientService;
        this.bookService = bookService;
    }

    public Borrowing borrowBook(Long clientId, Long bookId, LocalDate date) {
        Client client = clientService.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Клиент не найден"));
        Book book = bookService.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Книга не найдена"));
        Borrowing borrowing = new Borrowing(client, book, date);
        return borrowingRepo.save(borrowing);
    }

    public void returnBook(Long borrowingId) {
        borrowingRepo.deleteById(borrowingId);
    }

    public Page<Borrowing> findAllActive(Pageable pageable) {
        return borrowingRepo.findAllWithDetails(pageable);
    }

    public List<ReadingClientDTO> getAllReadingClients() {
        return borrowingRepo.findAllReadingClients();
    }
}
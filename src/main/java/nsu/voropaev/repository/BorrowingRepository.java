package nsu.voropaev.repository;

import nsu.voropaev.dto.ReadingClientDTO;
import nsu.voropaev.model.Borrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    @Query(value = "SELECT b FROM Borrowing b JOIN FETCH b.client JOIN FETCH b.book",
            countQuery = "SELECT COUNT(b) FROM Borrowing b")
    Page<Borrowing> findAllWithDetails(Pageable pageable);

    @Query("SELECT new nsu.voropaev.dto.ReadingClientDTO(" +
            "c.fullName, c.dateOfBirth, bk.name, bk.author, bk.isbn, b.borrowedDate) " +
            "FROM Borrowing b JOIN b.client c JOIN b.book bk")
    List<ReadingClientDTO> findAllReadingClients();
}
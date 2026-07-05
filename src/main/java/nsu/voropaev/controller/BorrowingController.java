package nsu.voropaev.controller;

import nsu.voropaev.service.BookService;
import nsu.voropaev.service.BorrowingService;
import nsu.voropaev.service.ClientService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/borrowings")
public class BorrowingController {

    private final BorrowingService borrowingService;
    private final ClientService clientService;
    private final BookService bookService;

    public BorrowingController(BorrowingService borrowingService,
                               ClientService clientService,
                               BookService bookService) {
        this.borrowingService = borrowingService;
        this.clientService = clientService;
        this.bookService = bookService;
    }

    @GetMapping
    public String list(Model model,
                       @PageableDefault(size = 10, sort = "borrowedDate") Pageable pageable) {
        model.addAttribute("page", borrowingService.findAllActive(pageable));
        model.addAttribute("clients", clientService.findAll(Pageable.unpaged()).getContent());
        model.addAttribute("books", bookService.findAll(Pageable.unpaged()).getContent());
        model.addAttribute("today", LocalDate.now());
        return "borrowings";
    }

    @PostMapping
    public String borrow(@RequestParam Long clientId,
                         @RequestParam Long bookId,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate borrowedDate) {
        borrowingService.borrowBook(clientId, bookId, borrowedDate);
        return "redirect:/borrowings";
    }

    @PostMapping("/delete/{id}")
    public String returnBook(@PathVariable Long id) {
        borrowingService.returnBook(id);
        return "redirect:/borrowings";
    }
}
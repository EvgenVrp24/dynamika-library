package nsu.voropaev.controller;

import nsu.voropaev.exception.NotFoundException;
import nsu.voropaev.model.Book;
import nsu.voropaev.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String list(Model model, @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        model.addAttribute("page", bookService.findAll(pageable));
        return "books";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) return "book-form";
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id)
                .orElseThrow(() -> new NotFoundException("Книга не найдена"));
        model.addAttribute("book", book);
        return "book-form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @Valid @ModelAttribute Book book, BindingResult result) {
        if (result.hasErrors()) return "book-form";
        book.setId(id);
        bookService.save(book);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }
}
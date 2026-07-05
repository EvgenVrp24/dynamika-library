package nsu.voropaev.controller;

import nsu.voropaev.dto.ReadingClientDTO;
import nsu.voropaev.service.BorrowingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/active")
public class ReadingClientRestController {

    private final BorrowingService borrowingService;

    public ReadingClientRestController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }

    @GetMapping
    public List<ReadingClientDTO> getReadingClients() {
        return borrowingService.getAllReadingClients();
    }
}
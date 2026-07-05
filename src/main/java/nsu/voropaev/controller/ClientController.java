package nsu.voropaev.controller;

import nsu.voropaev.exception.NotFoundException;
import nsu.voropaev.model.Client;
import nsu.voropaev.service.ClientService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String list(Model model, @PageableDefault(size = 10, sort = "fullName") Pageable pageable) {
        model.addAttribute("page", clientService.findAll(pageable));
        return "clients";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("client", new Client());
        return "client-form";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Client client, BindingResult result) {
        if (result.hasErrors()) return "client-form";
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Client client = clientService.findById(id)
                .orElseThrow(() -> new NotFoundException("Клиент не найден"));
        model.addAttribute("client", client);
        return "client-form";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @Valid @ModelAttribute Client client, BindingResult result) {
        if (result.hasErrors()) return "client-form";
        client.setId(id);
        clientService.save(client);
        return "redirect:/clients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientService.deleteById(id);
        return "redirect:/clients";
    }
}
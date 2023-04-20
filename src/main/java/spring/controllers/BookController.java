package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.dao.BookDAO;
import spring.models.Book;

import javax.validation.Valid;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;

    @Autowired
    public BookController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "book/index";
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id,
                       Model model){
        //realize!!!
        return null;
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("book") Book book){
        return "book/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "book/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,
                       @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") Book book,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "book/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }

}

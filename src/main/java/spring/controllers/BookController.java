package spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.dao.BookDAO;
import spring.dao.PersonDAO;
import spring.models.Book;
import spring.models.Person;

import javax.validation.Valid;


@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model,
                       @ModelAttribute("person") Person person){
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("person", bookDAO.showPerson(id));
        model.addAttribute("people", personDAO.index());
        return "book/show";
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

    @PatchMapping("/{id}/edit")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") Book book,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "book/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}")
    public String freeBook(@PathVariable("id") int id){
        bookDAO.updateBook(id);
        return "redirect:/books";
    }
    @PostMapping("/{id}")
    public String setBook(@ModelAttribute("person") Person person,
                          @PathVariable("id") int id){
        bookDAO.setBook(id, person.getId());
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

}

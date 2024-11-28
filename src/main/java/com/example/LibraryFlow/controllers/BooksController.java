package com.example.LibraryFlow.controllers;

import com.example.LibraryFlow.dao.BookDAO;
import com.example.LibraryFlow.dao.PersonDAO;
import com.example.LibraryFlow.models.Book;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;


    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @RequestMapping
    public String getAll(Model model){
        model.addAttribute("books", bookDAO.getAll());
        return "books/allBooks";
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        Book book = bookDAO.show(id);
        model.addAttribute("book", book);
        model.addAttribute("people", personDAO.getAll());
        model.addAttribute("book_owner", personDAO.show(book.getId_person()));
        return "books/show";
    }

    @PostMapping("/{id}/assign")
    public String assignBook(@PathVariable("id") long id, @RequestParam("personId") long personId){
        bookDAO.assignBook(id, personId);
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/remove")
    public String removeBook(@PathVariable("id") long id, @RequestParam("personId") long personId){
        bookDAO.removeAssigni(id, personId);
        return "redirect:/books/" + id;
    }

    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("books", new Book());
        return "books/create";
    }

    @PostMapping()
    public String saveBook(@ModelAttribute ("books") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/create";

        bookDAO.save(book);
        return "redirect:/books";
    }

    @RequestMapping("/{id}/update")
    public String update(Model model, @PathVariable("id") long id){
        model.addAttribute("books", bookDAO.show(id));
        return "books/update";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute ("books") @Valid Book book, BindingResult bindingResult, @PathVariable("id") long id){
        if(bindingResult.hasErrors())
            return "/books/update";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id, Model model){
        bookDAO.delete(id);
        return "redirect:/books";
    }



}

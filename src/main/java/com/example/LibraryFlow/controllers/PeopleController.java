package com.example.LibraryFlow.controllers;

import com.example.LibraryFlow.dao.BookDAO;
import com.example.LibraryFlow.dao.PersonDAO;
import com.example.LibraryFlow.models.Book;
import com.example.LibraryFlow.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;


    @Autowired
    public PeopleController(PersonDAO personDAO){
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getAll(Model model){
        model.addAttribute("person", personDAO.getAll());
        return "people/allPeople";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model){
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", personDAO.showBook(id));
        return "people/show";
    }

    @GetMapping("/create")
    public String create (Model model){
        model.addAttribute("person", new Person());
        return "people/create";
    }

    @PostMapping
    public String savePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "people/create";
        }

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/update")
    public String update (@PathVariable("id") long id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/update";
    }

    @PatchMapping("/{id}")
    public String updatePerson( @ModelAttribute("person") @Valid Person person, BindingResult bindingResult, @PathVariable("id") long id){

        if(bindingResult.hasErrors())
            return "people/update";

        personDAO.update(id, person);
        return ("redirect:/people");
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id, Model model){
        personDAO.delete(id);
        return "redirect:/people";
    }

}

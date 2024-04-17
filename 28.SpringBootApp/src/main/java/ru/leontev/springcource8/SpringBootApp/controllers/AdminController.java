package ru.leontev.springcource8.SpringBootApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.leontev.springcource8.SpringBootApp.models.Person;
import ru.leontev.springcource8.SpringBootApp.services.PeopleService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PeopleService peopleService;

    @Autowired
    public AdminController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String adminPage(Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("people", peopleService.findAll());
        return "admin/adminPage";
    }

    @PatchMapping("/add")
    public String makeAdmin(@ModelAttribute("person") Person person) {
        System.out.println(person.getId());
        return "redirect:/people";
    }
}

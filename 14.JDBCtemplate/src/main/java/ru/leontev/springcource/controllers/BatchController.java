package ru.leontev.springcource.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.leontev.springcource.dao.PersonDAO;

@Controller
@RequestMapping("/test-batch-update")
public class BatchController {
    private final PersonDAO personDAO;

    @Autowired
    public BatchController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index() {
        return "batch/index";
    }

    @GetMapping("/without")
    public String withoutBatch() {
        personDAO.testMultipleUpdate();
        return "redirect:/people";
    }

    @GetMapping("/with")
    public String withBatch() {
        personDAO.testBatchUpdate();
        return "redirect:/people";
    }

    @GetMapping("/delete-without")
    public String deleteWithoutBatch() {
        personDAO.testMultipleDelete();
        return "redirect:/people";
    }

    @GetMapping("/delete-with")
    public String deleteWithBatch() {
        personDAO.testBatchDelete();
        return "redirect:/people";
    }
}

package ru.leontev.springcource.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/third")
public class ThirdController {
    @GetMapping("/calculator")
    public String calculate(@RequestParam("a") int a, @RequestParam("b") int b,
                            @RequestParam("action") String action, Model model) {
        double answer;
        switch (action) {
            case "addition":
                answer = a + b;
                break;
            case "subtraction":
                answer = a - b;
                break;
            case "multiplication":
                answer = a * b;
                break;
            case "division":
                answer = a / (double)b;
                break;
            default:
                answer = 0;
                break;
        }
        System.out.println(answer);
        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("action", action);
        model.addAttribute("answer", answer);
        return "thirdd/calculator";
    }
}

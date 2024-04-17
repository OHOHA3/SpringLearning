package ru.leontev.springcource;

import org.springframework.stereotype.Component;

@Component("musicBean")
public class ClassicalMusic implements Music{
    public String getSong(){
        return "Hungarian Rhapsody";
    }
}

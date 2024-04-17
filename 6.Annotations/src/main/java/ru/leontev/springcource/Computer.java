package ru.leontev.springcource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Computer {
    int id;
    ClassicalMusicPlayer classicalMusicPlayer;

    public Computer(ClassicalMusicPlayer classicalMusicPlayer) {
        this.id = 1;
        this.classicalMusicPlayer = classicalMusicPlayer;
    }

    public void play() {
        System.out.print("Computer " + id + " ");
        classicalMusicPlayer.playMusic();
    }

    @PostConstruct
    public void start() {
        System.out.println("Computer started");
    }

    @PreDestroy
    public void end() {
        System.out.println("Computer ended");
    }
}

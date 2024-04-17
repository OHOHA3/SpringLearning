package ru.leontev.springcource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassicalMusicPlayer {
    @Autowired
    private ClassicalMusic classicalMusic;

    //@Autowired
    public ClassicalMusicPlayer(ClassicalMusic classicalMusic) {
        this.classicalMusic = classicalMusic;
    }

    //@Autowired
    public void setClassicalMusic(ClassicalMusic classicalMusic) {
        this.classicalMusic = classicalMusic;
    }

    public void playMusic() {
        System.out.println("Classical playing: " + classicalMusic.getSong());
    }
}

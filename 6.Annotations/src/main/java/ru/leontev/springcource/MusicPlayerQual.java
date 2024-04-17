package ru.leontev.springcource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component("forQual")
@Scope("prototype")
public class MusicPlayerQual {
    @Value("${musicPlayer.name}")
    private String name;
    @Value("${musicPlayer.volume}")
    private int volume;

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    private Music music1;
    private Music music2;

    @Autowired
    public MusicPlayerQual(@Qualifier("musicBean") Music music1, @Qualifier("rockMusic") Music music2) {
        this.music1 = music1;
        this.music2 = music2;
    }

    public void playMusic() {
        System.out.println("Playing: " + music1.getSong() + ", " + music2.getSong());
    }

    @PostConstruct
    public void start() {
        System.out.println("MusicPlayerQual started");
    }

    @PreDestroy
    public void end() {
        System.out.println("MusicPlayerQual ended");
    }
}

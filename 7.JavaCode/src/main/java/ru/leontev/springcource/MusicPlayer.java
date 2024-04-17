package ru.leontev.springcource;

import org.springframework.beans.factory.annotation.Value;

public class MusicPlayer {
    @Value("${musicPlayer.name}")
    String name;

    public String getName() {
        return name;
    }

    public int getVolume() {
        return volume;
    }

    @Value("${musicPlayer.volume}")
    int volume;
    private Music music1;
    private Music music2;

    //IoC
    public MusicPlayer(Music music1, Music music2) {
        this.music1 = music1;
        this.music2 = music2;
    }

    public void playMusic() {
        System.out.println("Playing: " + music1.getSong() + ", " + music2.getSong());
    }
}

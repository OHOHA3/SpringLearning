package ru.leontev.springcource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );

        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        musicPlayer.playMusic();
        Computer computer = context.getBean("computer", Computer.class);
        computer.play();
        System.out.println(musicPlayer.getName() + " " + musicPlayer.getVolume());
        ListMusicPlayer listMusicPlayer = context.getBean("listMusicPlayer",ListMusicPlayer.class);
        System.out.println(listMusicPlayer.getMusics());
        context.close();
    }
}

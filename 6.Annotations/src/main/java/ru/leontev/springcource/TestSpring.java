package ru.leontev.springcource;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );

        Music music = context.getBean("rockMusic", Music.class);
        MusicPlayer musicPlayer1 = new MusicPlayer(music);
        musicPlayer1.playMusic();
        music = context.getBean("musicBean", Music.class);
        MusicPlayer musicPlayer2 = new MusicPlayer(music);
        musicPlayer2.playMusic();
        System.out.println();

        ClassicalMusicPlayer classicalMusicPlayer =
                context.getBean("classicalMusicPlayer", ClassicalMusicPlayer.class);
        classicalMusicPlayer.playMusic();
        Computer computer = context.getBean("computer", Computer.class);
        computer.play();
        System.out.println();

        MusicPlayerQual musicPlayerQual = context.getBean("forQual", MusicPlayerQual.class);
        musicPlayerQual.playMusic();
        System.out.println(musicPlayerQual.getName() + " " + musicPlayerQual.getVolume());
        MusicPlayerQual musicPlayerQual1 = context.getBean("forQual", MusicPlayerQual.class);
        System.out.println(musicPlayerQual1 == musicPlayerQual);

        context.close();
    }
}

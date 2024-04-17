package ru.leontev.springcource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml"
        );
        MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        musicPlayer.playMusic();
        ClassicalMusic music = context.getBean("musicBean",ClassicalMusic.class);
        RockMusic rock = context.getBean("rockMusic", RockMusic.class);
        context.close();
    }
}

package ru.leontev.springcource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;

@Configuration
//@ComponentScan("ru.leontev.springcource")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {
    @Bean
    public ClassicalMusic musicBean() {
        return new ClassicalMusic();
    }

    @Bean
    public RockMusic rockMusic() {
        return new RockMusic();
    }

    @Bean
    SadMusic sadMusic() {
        return new SadMusic();
    }

    @Bean
    public MusicPlayer musicPlayer() {
        return new MusicPlayer(musicBean(), rockMusic());
    }

    @Bean
    public Computer computer() {
        return new Computer(musicPlayer());
    }

    @Bean
    public ArrayList<Music> musicList() {
        ArrayList<Music> list = new ArrayList<>();
        list.add(rockMusic());
        list.add(sadMusic());
        list.add(musicBean());
        return list;
       // return Arrays.asList(rockMusic(), sadMusic(), musicBean()); Но надо все в List переделать
    }

    @Bean
    public ListMusicPlayer listMusicPlayer() {
        return new ListMusicPlayer(musicList());
    }
}

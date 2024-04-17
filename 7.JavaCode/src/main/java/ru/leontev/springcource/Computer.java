package ru.leontev.springcource;

public class Computer {
    MusicPlayer musicPlayer;
    public Computer(MusicPlayer musicPlayer){
        this.musicPlayer = musicPlayer;
    }
    public void play(){
        System.out.print("Computer ");
        musicPlayer.playMusic();
    }
}

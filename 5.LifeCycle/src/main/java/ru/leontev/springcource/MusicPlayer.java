package ru.leontev.springcource;

public class MusicPlayer {
    public void doMyInit(){
        System.out.println("initializationPlayer");
    }
    public void doMyDestroy(){
        System.out.println("destroyPlayer");
    }
    private Music music;

    //IoC
    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }
}

package ru.leontev.springcource;

import java.util.ArrayList;

public class ListMusicPlayer {
    ArrayList<Music> musics;
    public ListMusicPlayer(ArrayList<Music> musics){
        this.musics = musics;
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }
}

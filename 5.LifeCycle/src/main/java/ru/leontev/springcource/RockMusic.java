package ru.leontev.springcource;

public class RockMusic implements Music {
    public void init(){
        System.out.println("rockInit");
    }
    public void destroy(){
        System.out.println("destroy");
    }
    public String getSong() {
        return "Wind cries Mary";
    }

}

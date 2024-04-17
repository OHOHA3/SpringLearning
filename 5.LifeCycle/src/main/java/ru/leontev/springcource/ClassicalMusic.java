package ru.leontev.springcource;

public class ClassicalMusic implements Music{

    private ClassicalMusic(){}
    public static ClassicalMusic factoryMethod(){
        System.out.println("makeClassicalMusic");
        return new ClassicalMusic();
    }
    public String getSong(){
        return "Hungarian Rhapsody";
    }
}

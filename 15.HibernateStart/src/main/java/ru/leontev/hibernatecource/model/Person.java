package ru.leontev.hibernatecource.model;

import javax.persistence.*;

//можно даже не использовать конструктор с аргументами и сеттеры
//обязателен пустой конструктор и поле с аннотацией Id
@Entity//пометка для хибернейта, что класс для работы с бд
@Table(name = "Person")//если название класса и таблы одинаковые, то можно не юзать
public class Person {
    @Id
    @Column(name = "id") //аналогично @Table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="some_name")
    @SequenceGenerator(name="some_name",
    sequenceName = "name_of_sequence_from_bd",
    allocationSize = 4//4,8,12...-шаг должен быть таким, какой в самой бд)*/
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

package ru.leontev.hibernatecource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.leontev.hibernatecource.model.Person;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        session.beginTransaction();

        //добавление данных
        System.out.println("Добавление данных:");
        Person person1 = new Person("Bob", 30);
        Person person2 = new Person("Andrew", 40);
        Person person3 = new Person("Tim", 20);
        session.save(person1);
        session.save(person2);
        session.save(person3);

        //получение данных
        System.out.println("Получение данных:");
        Person person4 = session.get(Person.class, 1);
        System.out.println(person4.getName());
        System.out.println(person4.getAge());

        //изменение данных
        System.out.println("Изменение данных:");
        Person person5 = session.get(Person.class, 26);
        person5.setName("new name");

        //удаление данных
        System.out.println("Удаление данных:");
        Person person6 = session.get(Person.class, 29);
        session.delete(person6);

        session.getTransaction().commit();
        //проверим айди
        System.out.println(person1.getId());

        sessionFactory.close();
    }
}

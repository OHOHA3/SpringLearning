package ru.leontev.hibernatecource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.leontev.hibernatecource.model.Item;
import ru.leontev.hibernatecource.model.Person;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = new Person("ABOBA", 12);
            person.addItem(new Item("item 1"));
            person.addItem(new Item("item 2"));
            person.addItem(new Item("item 3"));
            session.save(person);

            Person person3 = session.get(Person.class, 3);
            person3.getItems().add(new Item("adding item", person3));

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}

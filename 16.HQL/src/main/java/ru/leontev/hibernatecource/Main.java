package ru.leontev.hibernatecource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.leontev.hibernatecource.model.Person;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            List<Person> people = session.createQuery("FROM Person where age > 30").getResultList();
            for (Person person : people) {
                System.out.println(person);
            }

            List<Person> people1 = session.createQuery("FROM Person where name LIKE 'T%'").getResultList();
            for (Person person : people1) {
                System.out.println(person);
            }

            session.createQuery("update Person set name='Tim2' where age=20").executeUpdate();
            session.createQuery("delete from Person where age=21").executeUpdate();


            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}

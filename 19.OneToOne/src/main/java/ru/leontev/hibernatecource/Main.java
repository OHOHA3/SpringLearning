package ru.leontev.hibernatecource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.leontev.hibernatecource.model.Passport;
import ru.leontev.hibernatecource.model.Person;


public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            Person person = new Person("test", 50);
            Passport passport = new Passport(1234);
            person.setPassport(passport);
            session.save(person);

            Person person1 = session.get(Person.class, 5);
            System.out.println(person1.getPassport().getPassportNumber());

            Passport passport1 = session.get(Passport.class, 2);
            System.out.println(passport1.getPerson().getName());

            Person person2 = session.get(Person.class, 2);
            person2.getPassport().setPassportNumber(7777);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}

package ru.leontev.hibernatecource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.leontev.hibernatecource.model.Item;
import ru.leontev.hibernatecource.model.Person;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            /*Person person = session.get(Person.class, 38);
            System.out.println("person has been gotten");
            System.out.println(person.getItems());

            Item item = session.get(Item.class,80);
            //подгружаем связанные ленивые сущности
            Hibernate.initialize(item.getOwner());
            System.out.println("Item has been gotten");
            System.out.println(item.getOwner());*/

            //работа на нескольких транзакциях
            Item item = session.get(Item.class, 80);
            session.getTransaction().commit();
            System.out.println("Session closed");
            //мы во второй транзакции
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            item = (Item) session.merge(item);//пристегиваем объект ко второй сессии
            //пристегивание через HQL(у Алишева на примере взятия предметов по человеку
            //List<Item> items = session.createQuery("select i from Item i where i.owner.id=:personId",Item.class).setParameter("personId",person.getId()).getResultList();
            System.out.println(item.getOwner());

            //load и get
            Item item1 = new Item("Some new Item");
            Person personProxy = session.load(Person.class, 3);
            item.setOwner(personProxy);//load не тянет с бд, а заполняет нулями и юзает айди
            //с помощью этого можно опитимизированно добавить овнера, ибо нам нужен лишь его айди
        }
    }
}

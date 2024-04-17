package ru.leontev.hibernatecource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.leontev.hibernatecource.model.Item;
import ru.leontev.hibernatecource.model.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            //получения человека и его товаров
           /* Person person1 = session.get(Person.class, 3);
            System.out.println(person1);
            List<Item> items = person1.getItems();
            System.out.println(items);

            //получение человека через айтем
            Item item1 = session.get(Item.class, 5);
            System.out.println(item1);
            Person person2 = item1.getOwner();
            System.out.println(person2);*/

            //добавление айтема
            Person person3 = session.get(Person.class, 1);
            //добавляет в бд
            Item newItem1 = new Item("Item from Hibernate", person3);
            //обновляет кэш хайбернейта, чтобы в классе человека тоже был этот товар как и в бд
            person3.getItems().add(newItem1);
            //session.save(newItem1);

          /*  //добавление человека с айтемом
            Person person4 = new Person("Test person", 30);
            Item newItem2 = new Item("Item from Hibernate 2", person4);
            person4.setItems(new ArrayList<>(Collections.singletonList(newItem2)));
            session.save(person4);
            session.save(newItem2);

            //удаление айтемов
            Person person5 = session.get(Person.class,3);
            List<Item> items2= person5.getItems();
            for(Item item:items2){
                session.remove(item);//sql
            }
            person5.getItems().clear();//не sql, для кэша

            //удаление человека
            Person person6 = session.get(Person.class, 4);
            session.remove(person6);//sql
            person6.getItems().forEach(i->i.setOwner(null));//cache

            //изменение овнера
            Person person7 = session.get(Person.class, 5);
            Item item2 = session.get(Item.class, 1);
            item2.getOwner().getItems().remove(item2);
            item2.setOwner(person7);
            person7.getItems().add(item2);*/

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }
    }
}

package ru.leontev.springcource.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.leontev.springcource.models.Person;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {
    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class);

       /* //1 запрос
        List<Person> people = session.createQuery("select p from Person p", Person.class)
                .getResultList();
        //N запросов
        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        }*/
        //1 запрос, хэш, чтобы убрать дубляжи от джоина
        Set<Person> people = new HashSet<Person>(session.createQuery("select p from Person p LEFT JOIN FETCH p.items")
                .getResultList());//fetch говорит что загружаем связку в оперативку и берем из запроса все
        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        }
    }
}

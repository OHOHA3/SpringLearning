package ru.leontev.springcource.dao;

import org.springframework.stereotype.Component;
import ru.leontev.springcource.models.Person;

import java.util.ArrayList;

@Component
public class PersonDAO {
    private static int peopleIndex = 1;
    ArrayList<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(peopleIndex++, "Bob", 18, "bob@mail.ru"));
        people.add(new Person(peopleIndex++, "Mike",28,"mike@yandex.ru"));
        people.add(new Person(peopleIndex++, "John", 20,"jo@gmail.com"));
        people.add(new Person(peopleIndex++, "Tim", 49, "TimCock@gmail.com"));
    }

    public ArrayList<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(peopleIndex++);
        people.add(person);
    }

    public void update(int id, Person updatedPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}

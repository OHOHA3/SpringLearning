package ru.leontev.springcource.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.leontev.springcource.models.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        //return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        /*return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);*/
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Optional<Person> show(String email) {
        return jdbcTemplate.query("SELECT * FROM person WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(name, age, email, address) VALUES(?,?,?,?)", person.getName(), person.getAge(), person.getEmail(), person.getAddress());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET name=?,age=?,email=?, address=? WHERE id=?", updatedPerson.getName(),
                updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }


    ////////////////Speed test////////////////

    public void testMultipleUpdate() {
        List<Person> people = create1000People();

        long before = System.currentTimeMillis();
        for (Person person : people) {
            jdbcTemplate.update("INSERT INTO Person(name, age, email, address) VALUES(?,?,?,?)", person.getName(), person.getAge(), person.getEmail(), person.getAddress());
        }
        long after = System.currentTimeMillis();
        System.out.println("Time - " + (after - before));
    }

    public void testBatchUpdate() {
        List<Person> people = create1000People();

        long before = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("INSERT INTO Person(name, age, email, address) VALUES(?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setString(1, people.get(i).getName());
                        preparedStatement.setInt(2, people.get(i).getAge());
                        preparedStatement.setString(3, people.get(i).getEmail());
                        preparedStatement.setString(4, people.get(i).getAddress());
                    }

                    @Override
                    public int getBatchSize() {
                        return people.size();
                    }
                });
        long after = System.currentTimeMillis();
        System.out.println("Time - " + (after - before));
    }

    public void testMultipleDelete() {
        long before = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            jdbcTemplate.update("DELETE FROM Person WHERE id=?", i);
        }
        long after = System.currentTimeMillis();
        System.out.println("Time - " + (after - before));
    }

    public void testBatchDelete() {
        long before = System.currentTimeMillis();
        jdbcTemplate.batchUpdate("DELETE FROM Person WHERE id=?",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                        preparedStatement.setInt(1, i);
                    }

                    @Override
                    public int getBatchSize() {
                        return 1000;
                    }
                });
        long after = System.currentTimeMillis();
        System.out.println("Time - " + (after - before));
    }

    private List<Person> create1000People() {
        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name " + i, 30, i + "@google.com", "AA, AA, 830456"));
        }
        return people;
    }
}

package ru.leontev.springcource.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.leontev.springcource.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getInt("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setEmail(resultSet.getString("email"));
        person.setAddress(resultSet.getString("address"));
        return person;
    }
}

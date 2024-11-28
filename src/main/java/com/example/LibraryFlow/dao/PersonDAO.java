package com.example.LibraryFlow.dao;

import com.example.LibraryFlow.models.Book;
import com.example.LibraryFlow.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(Long id){
        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny().orElse(null);
    }

    //todo
    public List<Book> showBook(long id_person){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id_person=?", new Object[]{id_person}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO Person (fullName, birthYear) VALUES (?,?)", person.getFullName(),person.getBirthYear());
    }

    public void update(long id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fullName=?, birthYear=? WHERE id=?",
                updatedPerson.getFullName(), updatedPerson.getBirthYear(), id);
    }

    public void delete(long id){
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }

}

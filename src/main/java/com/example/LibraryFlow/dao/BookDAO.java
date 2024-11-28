package com.example.LibraryFlow.dao;

import com.example.LibraryFlow.models.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getAll(){
        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(long id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).
                stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book (title, author, publicationYear) VALUES (?, ?, ?)", book.getTitle(), book.getAuthor(), book.getPublicationYear());
    }

    public void update(long id, Book updatedBook){
        jdbcTemplate.update("UPDATE Book SET title=?, author=?, publicationYear=? WHERE id=?", updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getPublicationYear(), id);
    }

    public void delete(long id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
    }

    public void assignBook(long id, long id_person) {
        jdbcTemplate.update("UPDATE Book SET id_person=? WHERE id=?", id_person, id);
    }


    public Object removeAssigni(long id, long id_person){
       return jdbcTemplate.update("UPDATE Book SET id_person = NULL WHERE id = ? AND id_person = ?;", id, id_person);
    }

}

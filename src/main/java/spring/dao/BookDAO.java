package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.models.Book;
import spring.models.Person;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        return jdbcTemplate.query("SELECT * FROM book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id){
        return jdbcTemplate.query("SELECT * FROM book " +
                "WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }

    public Person showPerson(int id){
        return jdbcTemplate.query("SELECT person.id, person.name, person.year FROM book JOIN person ON person.id = book.user_id WHERE book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO book (name, author, year) VALUES (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE book SET name = ?, author = ?, year = ? " +
                "WHERE id = ?", updatedBook.getName(), updatedBook.getAuthor(),
                updatedBook.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM book " +
                "WHERE id = ?", id);
    }

    public void updateBook(int id) {
        jdbcTemplate.update("UPDATE book SET user_id = null WHERE book.id = ?", id);
    }

    public void setBook(int book_id, int person_id) {
        jdbcTemplate.update("UPDATE book SET user_id = ? WHERE book.id = ?", person_id, book_id);
    }
}

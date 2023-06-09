package spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.models.Book;
import spring.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public List<Book> showBooks(int id){
        return jdbcTemplate.query("SELECT book.name FROM PERSON JOIN book ON person.id = book.user_id WHERE person.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person (name, year) VALUES (?, ?)",
                person.getName(), person.getYear());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE person SET name = ?, year = ? WHERE id = ?",
                updatedPerson.getName(), updatedPerson.getYear(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE id = ?", id);
    }
}

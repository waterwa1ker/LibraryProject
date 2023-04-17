package spring.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    
    private int id;

    @NotEmpty(message = "Name should not be empty!")
    @Size(min=1, max=30, message="Name's length should be between 1 and 30!")
    private String name;

    @NotEmpty(message = "Author should not be empty!")
    @Size(min=1, max=30, message="Name's length should be between 1 and 30!")
    private String author;

    @Min(value = 0, message = "Year should be greater than 0!")
    private int year;


    public Book() {
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

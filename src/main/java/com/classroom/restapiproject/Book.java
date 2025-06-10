package com.classroom.restapiproject;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id // Marks this field as the primary key (unique identifier) for the book.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tells the database to automatically generate this ID.
    private Long id;
    private String title;
    private String isbn;

    // Constructors (special methods to create Book objects)
    public Book() {
        // Default constructor - JPA needs this!
    }

    public Book(String title,  String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    // Getters and Setters (methods to get and set the values of the fields)
    // Imagine these are like buttons to read or change the book's info.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // It's also good to have a toString() method for easy printing
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}

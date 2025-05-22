package com.classroom.restapiproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id // Marks this field as the primary key (unique identifier) for the book.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Tells the database to automatically generate this ID.
    private Long id;
    private String title;
    private String hashedPassword;
    private String plainPassword;
}

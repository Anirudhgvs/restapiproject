package com.classroom.restapiproject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

    @Id // 2. Marks this field as the Primary Key (unique identifier)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 3. Tells JPA to automatically generate this ID
    private Long id;
    private String name;
    private String email;

    // Constructors (special methods to create Student objects)
    // An empty one is often needed by JPA
    public Student() {
    }

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters (methods to get and set the values of the fields)
    // JPA uses these to access the fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // (Optional but good for printing) toString() method
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}

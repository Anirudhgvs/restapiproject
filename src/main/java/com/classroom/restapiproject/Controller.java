package com.classroom.restapiproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class Controller {

    @Autowired // 1. Spring will automatically give us a StudentRepository instance
    private StudentRepository studentRepository;

    // C - Create a new student
    @PostMapping // Handles HTTP POST requests to /api/students
    public Student createStudent(@RequestBody Student student) { // 2.
        return studentRepository.save(student); // 3.
    }

    // R - Read all students
    @GetMapping // Handles HTTP GET requests to /api/students
    public List<Student> getAllStudents() {
        return studentRepository.findAll(); // 4.
    }

    // R - Read a single student by ID
    @GetMapping("/{id}") // Handles HTTP GET requests to /api/students/{id} (e.g., /api/students/1)
    public Optional<Student> getStudentById(@PathVariable Long id) { // 5.
        return studentRepository.findById(id); // 6.
    }

    // U - Update a student
    @PutMapping("/{id}") // Handles HTTP PUT requests to /api/students/{id}
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id)); // Basic error handling

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        return studentRepository.save(student); // 7. save() is also used for updates if the ID exists
    }

    // D - Delete a student
    @DeleteMapping("/{id}") // Handles HTTP DELETE requests to /api/students/{id}
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id); // 8.
        return "Student with id " + id + " has been deleted.";
    }

}

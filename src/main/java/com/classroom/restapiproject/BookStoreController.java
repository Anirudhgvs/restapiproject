package com.classroom.restapiproject;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/books")
public class BookStoreController {
    // ... other dependencies ...

    // Anyone (even unauthenticated if you allowed it in filter chain config) can GET all books
    // If /books/** requires authentication, then only authenticated users can GET
    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks() {
        // ... logic to get all books ...
        return ResponseEntity.ok(Collections.emptyList()); // Placeholder
    }

    // Only authenticated users with the 'ADMIN' role can add a book
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')") // Annotation to require ADMIN role
    public ResponseEntity<Book> addBook(@RequestBody Book newBook) {
        // If we reach here, the user is authenticated AND has the ADMIN role
        // ... logic to add book ...
        return ResponseEntity.ok(newBook); // Placeholder
    }

    // Only authenticated users with the 'ADMIN' role can delete a book
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Annotation to require ADMIN role
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        // If we reach here, the user is authenticated AND has the ADMIN role
        // ... logic to delete book ...
        return ResponseEntity.noContent().build(); // Placeholder
    }

    // You can check if the user is just authenticated (any logged-in user)
    // @PreAuthorize("isAuthenticated()")

    // Or check if the user has *any* of several roles
    // @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
}

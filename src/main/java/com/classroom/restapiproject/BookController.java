package com.classroom.restapiproject;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    CacheInspectionService cis;

    // Get all books
    @GetMapping
    public void getAllBooks() {
        cis.printCacheContents("books");
    }

    // Get a specific book by ID
    @GetMapping("/{id}")
    @Cacheable(value = "books", key = "#id", unless = "#result == null")
    public Book getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        return null;
    }

    // Create a new book
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookRepository.save(book);
        System.out.println("Created and cached new book with ID: " + savedBook.getId());
        return ResponseEntity.ok(savedBook);
    }

    // Update a book by ID
    @PutMapping("/{id}")
    @CachePut(value = "books", key = "#id")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);
        Book updatedBookEntry = null;
        if (existingBook.isPresent()) {
            updatedBook.setId(id);
            updatedBookEntry = bookRepository.save(updatedBook);
            return updatedBookEntry;
        }
        return null;
    }

    // Delete a book by ID
    @DeleteMapping("/{id}")
    @CacheEvict(value = "books", key = "#id")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
package com.classroom.restapiproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class HelloWorldController {

    private List<Book> books = new ArrayList<>();

    public HelloWorldController() {
        // Add some sample books when the controller is created
        books.add(new Book(1L, "The Hobbit", "J.R.R. Tolkien"));
        books.add(new Book(2L, "Pride and Prejudice", "Jane Austen"));
    }


    @GetMapping
    public ResponseEntity<?> getBookById(@PathVariable String author,
            @RequestParam(value = "id", required = true) Long id) {

        //Checking if the book is present in my array list
        for (Integer i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if(id == book.getId()){
                return ResponseEntity.ok(book);
            }
        }
        throw new BookNotFoundException(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createABook(@RequestBody Book bookRequest) {
        books.add(bookRequest);
        return bookRequest;
    }


}

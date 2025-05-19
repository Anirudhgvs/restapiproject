package com.classroom.restapiproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> books = new ArrayList<>();

    public BookController() {
        // Add some sample books when the controller is created
        books.add(new Book("The Hobbit", "J.R.R. Tolkien","214324"));
        books.add(new Book( "Pride and Prejudice", "Jane Austen","45455"));
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

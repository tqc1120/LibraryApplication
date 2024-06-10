package com.example.LibraryApp.controller;

import com.example.LibraryApp.entity.Book;
import com.example.LibraryApp.service.BookService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(params = "id")
    public ResponseEntity<Book> getBookById(@RequestParam("id")  Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping(params = "id")
    public ResponseEntity<Book> updateBook(@RequestParam("id")  Long id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteBook(@RequestParam("id")  Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

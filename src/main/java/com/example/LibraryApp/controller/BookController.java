package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(params = "id")
    public ResponseEntity<BookDto> getBookById(@RequestParam("id")  Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }
//
//    @GetMapping(params = "title")
//    public ResponseEntity<List<BookDto>> findBooksByTitle(@RequestParam("title") String title) {
//        return new ResponseEntity<>(bookService.getBooksByTitle(title), HttpStatus.OK);
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
//        return new ResponseEntity<>(bookService.createBook(bookDto), HttpStatus.CREATED);
//    }
//
//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<BookDto> updateBook(@RequestParam("id") Long id, @RequestBody BookDto bookDto) {
//        BookDto updatedBook = bookService.updateBook(id, bookDto);
//        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
//    }
//
//    @DeleteMapping(params = "id")
//    public ResponseEntity<Void> deleteBook(@RequestParam("id")  Long id) {
//        bookService.deleteBook(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

}

package com.example.LibraryApp.controller;

import com.example.LibraryApp.entity.Author;
import com.example.LibraryApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(params = "id")
    public ResponseEntity<Author> getAuthorById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.createAuthor(author), HttpStatus.CREATED);
    }

    @PutMapping(params = "id")
    public ResponseEntity<Author> updateAuthor(@RequestParam("id") Long id, @RequestBody Author author) {
        return new ResponseEntity<>(authorService.updateAuthor(id, author), HttpStatus.OK);
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<Void> deleteAuthor(@RequestParam("id") Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.service.AuthorService;
import com.example.LibraryApp.service.impl.AuthorServiceImpl;
import io.micrometer.common.util.internal.logging.AbstractInternalLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(params = "id")
    public ResponseEntity<AuthorDto> getAuthorById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }
//
//    @GetMapping(params = "name")
//    public ResponseEntity<List<AuthorDto>> findAuthorByName(@RequestParam("name") String name) {
//        return new ResponseEntity<>(authorService.getAuthorByName(name), HttpStatus.OK);
//    }
//
//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
//        return new ResponseEntity<>(authorService.createAuthor(authorDto), HttpStatus.CREATED);
//    }
//
//    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<AuthorDto> updateAuthor(@RequestParam("id") Long id, @RequestBody AuthorDto authorDto) {
//        AuthorDto updatedAuthor = authorService.updateAuthor(id, authorDto);
//        return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
//    }
//
//    @DeleteMapping(params = "id")
//    public ResponseEntity<Void> deleteAuthor(@RequestParam("id") Long id) {
//        authorService.deleteAuthor(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}

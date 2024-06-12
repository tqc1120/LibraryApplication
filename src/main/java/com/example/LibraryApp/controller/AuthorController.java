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

    @GetMapping(params = "name")
    public List<AuthorDto> getAuthorsByName(@RequestParam("name") String name) {
        return authorService.getAuthorsByName(name);
    }
}

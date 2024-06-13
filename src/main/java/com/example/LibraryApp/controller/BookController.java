package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.service.SearchService;
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
    private final SearchService searchService;

    @Autowired
    public BookController( SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(params = "id")
    public ResponseEntity<BookDto> getBookById(@RequestParam("id")  Long id) {
        return new ResponseEntity<>(searchService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping(params = "title")
    public List<BookDto> getBooksByTitle(@RequestParam("title") String title) {
        return searchService.getBooksByTitle(title);
    }
}

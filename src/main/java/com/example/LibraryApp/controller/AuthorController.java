package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.service.ManageService;
import com.example.LibraryApp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final SearchService searchService;
    private final ManageService manageService;
//    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Autowired
    public AuthorController(SearchService searchService, ManageService manageService) {
        this.searchService = searchService;
        this.manageService = manageService;
    }

    @GetMapping(params = "id")
    public ResponseEntity<AuthorDto> getAuthorById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(searchService.getAuthorById(id), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public List<AuthorDto> getAuthorsByName(@RequestParam("name") String name) {
        return searchService.getAuthorsByName(name);
    }

    @PostMapping
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return manageService.createAuthor(authorDto);
    }
}

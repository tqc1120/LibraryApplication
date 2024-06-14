package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.service.ManageService;
import com.example.LibraryApp.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@Tag(name = "Authors", description = "API for managing authors")
public class AuthorController {
    private final SearchService searchService;
    private final ManageService manageService;

    @Autowired
    public AuthorController(SearchService searchService, ManageService manageService) {
        this.searchService = searchService;
        this.manageService = manageService;
    }

    @GetMapping(params = "id")
    @Operation(summary = "Get author by ID", description = "Returns the details of an author by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the author",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content) })
    public ResponseEntity<AuthorDto> getAuthorById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(searchService.getAuthorById(id), HttpStatus.OK);
    }

    @GetMapping(params = "name")
    @Operation(summary = "Get authors by name", description = "Returns a list of authors that match the given name.")
    public List<AuthorDto> getAuthorsByName(@RequestParam("name") String name) {
        return searchService.getAuthorsByName(name);
    }

    @PostMapping
    @Operation(summary = "Create a new author", description = "Creates a new author and returns the details of the created author.")
    public AuthorDto createAuthor(@RequestBody AuthorDto authorDto) {
        return manageService.createAuthor(authorDto);
    }

    @PutMapping(params = "id")
    @Operation(summary = "Update author name", description = "Updates the name of an existing author by their ID.")
    public AuthorDto updateAuthorName(@RequestParam("id") Long id, @RequestBody String newName) {
        return manageService.updateAuthorName(id, newName);
    }
}

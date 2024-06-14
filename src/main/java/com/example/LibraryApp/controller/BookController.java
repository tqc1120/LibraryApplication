package com.example.LibraryApp.controller;

import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.service.ManageService;
import com.example.LibraryApp.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "API for managing books")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the book",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = BookDto.class)) }),
        @ApiResponse(responseCode = "404", description = "Book not found",
                content = @Content) })
public class BookController {
    private final SearchService searchService;
    private final ManageService manageService;

    @Autowired
    public BookController(SearchService searchService, ManageService manageService) {
        this.searchService = searchService;
        this.manageService = manageService;
    }

    @GetMapping(params = "id")
    @Operation(summary = "Get book by ID", description = "Returns the details of a book by its ID.")
    public ResponseEntity<BookDto> getBookById(@RequestParam("id")  Long id) {
        return new ResponseEntity<>(searchService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping(params = "title")
    @Operation(summary = "Get books by title", description = "Returns a list of books that match the given title.")
    public List<BookDto> getBooksByTitle(@RequestParam("title") String title) {
        return searchService.getBooksByTitle(title);
    }

    @DeleteMapping(params = "id")
    @Operation(summary = "Delete a book by ID", description = "Deletes a book by its ID.")
    public void deleteBookById(@RequestParam("id") Long bookId) {
        manageService.deleteBook(bookId);
    }
}

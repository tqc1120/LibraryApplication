package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.domain.entity.BookAuthor;
import com.example.LibraryApp.domain.entity.BookAuthorId;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.BookAuthorRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.service.impl.SearchServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookAuthorRepository bookAuthorRepository;

    @InjectMocks
    private SearchServiceImpl searchService;

    @Test
    void  testGetBookById() {
        // Setup behavior
        Author mockAuthor = new Author(1L, "Joshua Bloch");
        Book mockBook = new Book(1L, "Effective Java");

        BookAuthorId bookAuthorId = new BookAuthorId(1L, 1L);
        BookAuthor mockBookAuthor = new BookAuthor(bookAuthorId, mockAuthor, mockBook);

        mockBook.setBookAuthors(Set.of(mockBookAuthor));
        mockAuthor.setBookAuthors(Set.of(mockBookAuthor));

        // Mock
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));
        BookDto result = searchService.getBookById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getBookId());
        assertEquals("Effective Java", result.getTitle());
        assertTrue(result.getAuthors().contains("Joshua Bloch"));
        verify(bookRepository, times(1)).findById(1L);
    }
}

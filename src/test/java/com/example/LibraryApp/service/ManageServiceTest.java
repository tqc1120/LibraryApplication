package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.domain.entity.BookAuthor;
import com.example.LibraryApp.domain.entity.BookAuthorId;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.BookAuthorRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.service.impl.ManageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManageServiceTest {
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookAuthorRepository bookAuthorRepository;

    @InjectMocks
    private ManageServiceImpl manageService;

    @Test
    void testDeleteBookById() {
        // Setup behavior
        Author mockAuthor = new Author(1L, "Joshua Bloch");
        Book mockBook = new Book(1L, "Effective Java");

        BookAuthorId bookAuthorId = new BookAuthorId(1L, 1L);
        BookAuthor mockBookAuthor = new BookAuthor(bookAuthorId, mockAuthor, mockBook);

        mockBook.setBookAuthors(Set.of(mockBookAuthor));
        mockAuthor.setBookAuthors(Set.of(mockBookAuthor));

        // Mock
        when(bookRepository.findById(1L)).thenReturn(Optional.of(mockBook));
        doNothing().when(bookRepository).deleteById(1L);
        doNothing().when(bookAuthorRepository).deleteAllInBatch(mockBook.getBookAuthors());
        manageService.deleteBook(1L);

        // Assert
        verify(bookRepository, times(1)).findById(1L);
        verify(bookAuthorRepository, times(1)).deleteAllInBatch(mockBook.getBookAuthors());
        verify(bookRepository, times(1)).deleteById(1L);
    }
}

package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.exception.ResourceNotFoundException;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.BookAuthorRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookAuthorRepository bookAuthorRepository;

    @Autowired
    public SearchServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, BookAuthorRepository bookAuthorRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return mapToAuthorDto(author);
    }

    @Override
    public List<AuthorDto> getAuthorsByName(String name) {
        List<Author> authors = authorRepository.getAuthorsByName(name);
        if (authors.isEmpty()) {
            throw new ResourceNotFoundException("No authors found with the name: " + name);
        }
        return authors.stream().map(this::mapToAuthorDto).collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return mapToBookDto(book);
    }

    @Override
    public List<BookDto> getBooksByTitle(String title) {
        Optional<Book> books = bookRepository.findByTitle(title);
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No books found with the title: " + title);
        }
        return books.stream().map(this::mapToBookDto).collect(Collectors.toList());
    }

    private BookDto mapToBookDto(Book book) {
        BookDto dto = new BookDto();
        dto.setBookId(book.getBook_id());
        dto.setTitle(book.getTitle());
        dto.setAuthors(book.getBookAuthors().stream().map(bookAuthor -> bookAuthor.getAuthor().getName()).collect(Collectors.toSet()));
        return dto;
    }

    private AuthorDto mapToAuthorDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setAuthorId(author.getAuthor_id());
        dto.setName(author.getName());
        dto.setBooks(author.getBookAuthors().stream().map(bookAuthor -> bookAuthor.getBook().getTitle()).collect(Collectors.toSet()));
        return dto;
    }
}

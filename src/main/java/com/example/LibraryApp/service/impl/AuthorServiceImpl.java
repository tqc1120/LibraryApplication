package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.exception.DuplicateResourceException;
import com.example.LibraryApp.exception.ResourceNotFoundException;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.BookAuthorRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.service.AuthorService;
import com.example.LibraryApp.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookAuthorRepository bookAuthorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, BookAuthorRepository bookAuthorRepository) {

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

//    @Override
//    public AuthorDto createAuthor(AuthorDto authorDto) {
//        authorRepository.save(mapToAuthor(authorDto));
//        return authorDto;
//    }

    // mappers
    private AuthorDto mapToAuthorDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setAuthorId(author.getAuthor_id());
        dto.setName(author.getName());
        dto.setBooks(author.getBookAuthors().stream().map(bookAuthor -> bookAuthor.getBook().getTitle()).collect(Collectors.toSet()));
        return dto;
    }

//    private Author mapToAuthor(AuthorDto authorDto) {
//        Author author = new Author();
//        author.setName(authorDto.getName());
//
//        if (authorDto.getBooks() != null && !authorDto.getBooks().isEmpty()) {
//            Set<Book> books = new HashSet<>();
//            for (String bookTitle : authorDto.getBooks()) {
//                List<Book> new_books = bookRepository.findByTitle(bookTitle);
//                // 1.
//
//                for (Book new_book : new_books) {
//                    if (new_book == null) {
//                        book = new Book();
//                        book.setTitle(bookTitle);
//                        book = bookRepository.save(book);
//                    } else {
//                        book = new_book.get();
//                    }
//                }
//                books.add(book);
//            }
//            author.setBooks(books);
//        } else {
//            Set<Book> emptyBookList = new HashSet<>();
//            author.setBooks(emptyBookList);
//        }
//        return author;
//    }
}

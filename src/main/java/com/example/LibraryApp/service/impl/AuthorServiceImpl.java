package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.exception.DuplicateResourceException;
import com.example.LibraryApp.exception.ResourceNotFoundException;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.CustomAuthorRepository;
import com.example.LibraryApp.repository.CustomBookRepository;
import com.example.LibraryApp.service.AuthorService;
import com.example.LibraryApp.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CustomAuthorRepository customAuthorRepository;
    private final CustomBookRepository customBookRepository;
    private final BookService bookService;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, CustomAuthorRepository customAuthorRepository, BookRepository bookRepository, CustomBookRepository customBookRepository, BookService bookService) {
        this.authorRepository = authorRepository;
        this.customAuthorRepository = customAuthorRepository;
        this.bookRepository = bookRepository;
        this.customBookRepository = customBookRepository;
        this.bookService = bookService;
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return new AuthorDto(
                author.getAuthor_id(),
                author.getName(),
                author.getBooks().stream()
                        .map(Book::getTitle)
                        .collect(Collectors.toList()));
    }

    public List<AuthorDto> getAuthorByName(String name) {
        if (!customAuthorRepository.existsByName(name)) {
            throw new ResourceNotFoundException("Author not found");
        }
        return customAuthorRepository.getAuthorByName(name).stream()
                .map(author -> new AuthorDto(
                        author.getAuthor_id(),
                        author.getName(),
                        author.getBooks().stream()
                                .map(Book::getTitle)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    };

    @Override
    public Author createAuthor(Author author) {
        if (customAuthorRepository.existsByName(author.getName())) {
            throw new DuplicateResourceException("Author already exists");
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author authorDetails) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        logger.debug("Received author details: {}", authorDetails);
        logger.debug("Books received: {}", authorDetails.getBooks());

        if (authorDetails.getName() != null) {
            existingAuthor.setName(authorDetails.getName());
            logger.debug("Updated author's name to: {}", authorDetails.getName());
        }

        if (authorDetails.getBooks() != null) {
            Set<Book> books = new HashSet<>();
            for (Book book : authorDetails.getBooks()) {
                logger.debug("Processing book: {}", book);
                Book existingBook = bookRepository.findById(book.getBook_id()).orElse(null);
                if (existingBook != null) {
                    books.add(existingBook);
                    logger.debug("Added existing book to author: {}", existingBook);
                } else {
                    books.add(bookService.save(book));
                    logger.debug("Saved and added new book to author: {}", book);
                }
            }
            existingAuthor.setBooks(books);

            Set<Book> tempBooks = new HashSet<>(books);
            for (Book book : tempBooks) {
                book.getAuthors().add(existingAuthor);
                bookService.save(book);
                logger.debug("Updated book's authors and saved: {}", book);
            }
            logger.debug("Updated books for author: {}", existingAuthor.getBooks());
        } else {
            logger.debug("No books provided for update.");
        }
        Author updatedAuthor = authorRepository.save(existingAuthor);
        logger.debug("Updated author: {}", updatedAuthor);
        return updatedAuthor;
    }


    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found");
        }
        authorRepository.deleteById(id);
    }
}

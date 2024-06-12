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

//@Service
//@Transactional
//public class AuthorServiceImpl implements AuthorService {
//    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
//
//    private final AuthorRepository authorRepository;
//    private final BookRepository bookRepository;
//    private final CustomAuthorRepository customAuthorRepository;
//    private final CustomBookRepository customBookRepository;
//    private final BookService bookService;
//
//    @Autowired
//    public AuthorServiceImpl(AuthorRepository authorRepository, CustomAuthorRepository customAuthorRepository, BookRepository bookRepository, CustomBookRepository customBookRepository, BookService bookService) {
//        this.authorRepository = authorRepository;
//        this.customAuthorRepository = customAuthorRepository;
//        this.bookRepository = bookRepository;
//        this.customBookRepository = customBookRepository;
//        this.bookService = bookService;
//    }
//
//    @Override
//    public AuthorDto getAuthorById(Long id) {
//        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
//        return convertToDto(author);
//    }
//
//    @Override
//    public List<AuthorDto> getAuthorByName(String name) {
//        if (!customAuthorRepository.existsByName(name)) {
//            throw new ResourceNotFoundException("Author not found");
//        }
//        return customAuthorRepository.getAuthorByName(name).stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public AuthorDto createAuthor(AuthorDto authorDto) {
//        if (customAuthorRepository.existsByName(authorDto.getName())) {
//            throw new DuplicateResourceException("Author already exists");
//        }
//        Author author = convertToEntity(authorDto);
//        Author savedAuthor = authorRepository.save(author);
//        return convertToDto(savedAuthor);
//    }
//
//    @Override
//    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
//        Author existingAuthor = authorRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
//
//        logger.debug("Received author details: {}", authorDto);
//        logger.debug("Books received: {}", authorDto.getBooks());
//
//        if (authorDto.getName() != null) {
//            existingAuthor.setName(authorDto.getName());
//            logger.debug("Updated author's name to: {}", authorDto.getName());
//        }
//
//        if (authorDto.getBooks() != null) {
//            List<Book> booksToUpdate = new ArrayList<>();
//            for (BookDto bookDto : authorDto.getBooks()) {
//                logger.debug("Processing book: {}", bookDto);
//
//                Book existingBook = null;
//                if (bookDto.getBookId() != null) {
//                    existingBook = bookRepository.findById(bookDto.getBookId()).orElse(null);
//                }
//
//                if (existingBook != null) {
//                    booksToUpdate.add(existingBook);
//                    logger.debug("Added existing book to author: {}", existingBook);
//                } else {
//                    Book newBook = convertToEntity(bookDto);
//                    booksToUpdate.add(bookRepository.save(newBook));
//                    logger.debug("Saved and added new book to author: {}", newBook);
//                }
//            }
//
//            existingAuthor.getBooks().clear();  // Clear existing books to avoid duplicates
//            existingAuthor.getBooks().addAll(booksToUpdate);  // Add updated books
//
//            // Update new book associations
//            for (Book book : booksToUpdate) {
//                book.getAuthors().add(existingAuthor);  // Ensure bi-directional relationship
//                bookRepository.save(book);  // Save updated book
//                logger.debug("Updated book's authors and saved: {}", book);
//            }
//
//            logger.debug("Updated books for author: {}", existingAuthor.getBooks());
//        } else {
//            logger.debug("No books provided for update.");
//        }
//        Author updatedAuthor = authorRepository.save(existingAuthor);
//        logger.debug("Updated author: {}", updatedAuthor);
//        return convertToDto(updatedAuthor);
//    }
//
//    @Override
//    public void deleteAuthor(Long id) {
//        if (!authorRepository.existsById(id)) {
//            throw new ResourceNotFoundException("Author not found");
//        }
//        authorRepository.deleteById(id);
//    }
//
//    public AuthorDto convertToDto(Author author) {
//        AuthorDto authorDto = new AuthorDto();
//        authorDto.setAuthorId(author.getAuthor_id());
//        authorDto.setName(author.getName());
//        authorDto.setBooks(author.getBooks().stream().map(this::convertToDto).collect(Collectors.toList()));
//        return authorDto;
//    }
//
//    public BookDto convertToDto(Book book) {
//        BookDto bookDto = new BookDto();
//        bookDto.setBookId(book.getBook_id());
//        bookDto.setTitle(book.getTitle());
//        return bookDto;
//    }
//
//    public Author convertToEntity(AuthorDto authorDto) {
//        Author author = new Author();
//        author.setAuthor_id(authorDto.getAuthorId());
//        author.setName(authorDto.getName());
//        author.setBooks(authorDto.getBooks().stream().map(this::convertToEntity).collect(Collectors.toSet()));
//        return author;
//    }
//
//    public Book convertToEntity(BookDto bookDto) {
//        Book book = new Book();
//        book.setBook_id(bookDto.getBookId());
//        book.setTitle(bookDto.getTitle());
//        return book;
//    }
}

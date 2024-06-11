package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.exception.DuplicateResourceException;
import com.example.LibraryApp.exception.ResourceNotFoundException;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.CustomAuthorRepository;
import com.example.LibraryApp.repository.CustomBookRepository;
import com.example.LibraryApp.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CustomBookRepository customBookRepository;
    private final CustomAuthorRepository customAuthorRepository;

    @Autowired
    public BookServiceImpl(AuthorRepository authorRepository, CustomAuthorRepository customAuthorRepository, BookRepository bookRepository, CustomBookRepository customBookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.customBookRepository = customBookRepository;
        this.customAuthorRepository = customAuthorRepository;
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return new BookDto(
                book.getBook_id(),
                book.getTitle(),
                book.getAuthors().stream()
                        .map(Author::getName).
                        collect(Collectors.toList()));
    }

    public List<BookDto> getBooksByTitle(String title) {
        if (!customBookRepository.existsByTitle(title)) {
            throw new DuplicateResourceException("Book not found");
        }
        return customBookRepository.getBooksByTitle(title).stream()
                .map(book -> new BookDto(
                        book.getBook_id(),
                        book.getTitle(),
                        book.getAuthors().stream()
                                .map(Author::getName)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    @Override
    public Book createBook(Book book) {
        if (customBookRepository.existsByTitle(book.getTitle())) {
            throw new DuplicateResourceException("Book already exists");
        }
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        if (book.getTitle() != null) {
            existingBook.setTitle(book.getTitle());
        }

        if (book.getAuthors() != null) {
            for (Author newAuthor : book.getAuthors()) {
                if (!customAuthorRepository.existsByName(newAuthor.getName())) {
                    authorRepository.save(newAuthor);
                }
                newAuthor = authorRepository.findByName(newAuthor.getName());
                existingBook.getAuthors().add(newAuthor);
            }
        }

        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }

    public Book save(Book book) {
        logger.debug("Saving book: {}", book);
        return bookRepository.save(book);
    }
}

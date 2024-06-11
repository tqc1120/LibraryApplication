package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.exception.DuplicateResourceException;
import com.example.LibraryApp.exception.ResourceNotFoundException;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.CustomBookRepository;
import com.example.LibraryApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CustomBookRepository customBookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CustomBookRepository customBookRepository) {
        this.bookRepository = bookRepository;
        this.customBookRepository = customBookRepository;
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
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found");
        }
        book.setBook_id(id);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}

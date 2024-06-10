package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
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
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            return new BookDto(
                    book.getBook_id(),
                    book.getTitle(),
                    book.getAuthors().stream()
                            .map(Author::getName).
                            collect(Collectors.toList()));
        }
        return null;
    }

    public List<BookDto> getBooksByTitle(String title) {
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
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)) {
            book.setBook_id(id);
            return bookRepository.save(book);
        } else {
            return null;
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}

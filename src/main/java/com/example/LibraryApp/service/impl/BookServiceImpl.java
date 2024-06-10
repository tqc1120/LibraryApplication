package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.entity.Book;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
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

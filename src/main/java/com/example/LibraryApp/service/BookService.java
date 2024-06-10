package com.example.LibraryApp.service;

import com.example.LibraryApp.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface BookService {
    Book getBookById(Long id);
    Book createBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
}

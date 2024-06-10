package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.entity.Book;

import java.util.List;

public interface CustomBookRepository {
    List<Book> getBooksByTitle(String title);
}

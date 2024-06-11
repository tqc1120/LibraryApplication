package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomBookRepository {
    List<Book> getBooksByTitle(String title);
    boolean existsByTitle(String title);
}

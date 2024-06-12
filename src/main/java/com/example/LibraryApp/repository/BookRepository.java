package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, CustomBookRepository {
    List<Book> findByTitle(String title);
}

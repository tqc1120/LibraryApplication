package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}

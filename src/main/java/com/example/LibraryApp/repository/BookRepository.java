package com.example.LibraryApp.repository;

import com.example.LibraryApp.entity.Author;
import com.example.LibraryApp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {}

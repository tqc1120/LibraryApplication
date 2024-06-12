package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.entity.BookAuthor;
import com.example.LibraryApp.domain.entity.BookAuthorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, BookAuthorId> {
}

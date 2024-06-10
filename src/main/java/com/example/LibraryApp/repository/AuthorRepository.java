package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}

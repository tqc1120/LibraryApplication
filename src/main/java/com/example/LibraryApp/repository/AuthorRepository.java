package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, CustomAuthorRepository {
    List<Author> findByName(String name);
}

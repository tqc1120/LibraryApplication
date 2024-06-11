package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomAuthorRepository {
    List<Author> getAuthorByName(String name);
    boolean existsByName(String name);
}

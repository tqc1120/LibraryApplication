package com.example.LibraryApp.repository;

import com.example.LibraryApp.domain.entity.Author;

import java.util.List;

public interface CustomAuthorRepository {
    List<Author> getAuthorByName(String name);
}

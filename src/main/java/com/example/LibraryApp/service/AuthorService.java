package com.example.LibraryApp.service;

import com.example.LibraryApp.entity.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    Author getAuthorById(Long id);
    Author createAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
}

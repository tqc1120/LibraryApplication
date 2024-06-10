package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.domain.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    AuthorDto getAuthorById(Long id);
    Author createAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);

    List<AuthorDto> getAuthorByName(String name);
}

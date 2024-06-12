package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.domain.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    AuthorDto getAuthorById(Long id);
    List<AuthorDto> getAuthorsByName(String name);
    AuthorDto createAuthor(AuthorDto authorDto);
//    AuthorDto updateAuthor(Long id, AuthorDto authorDto);
//    void deleteAuthor(Long id);
}

package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.dto.AuthorDto;

public interface ManageService {
    AuthorDto createAuthor(AuthorDto authorDto);
//    AuthorDto updateAuthor(Long id, AuthorDto authorDto);
    void deleteBook(Long bookId);
}

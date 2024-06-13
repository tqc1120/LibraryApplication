package com.example.LibraryApp.service;

import com.example.LibraryApp.domain.dto.AuthorDto;

public interface ManageService {
    AuthorDto createAuthor(AuthorDto authorDto);
    AuthorDto updateAuthorName(Long id, String newName);
    void deleteBook(Long bookId);
}

package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.exception.DuplicateResourceException;
import com.example.LibraryApp.exception.ResourceNotFoundException;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.service.AuthorService;
import com.example.LibraryApp.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return mapToAuthorDto(author);
    }

    // mappers
    private AuthorDto mapToAuthorDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setAuthorId(author.getAuthor_id());
        dto.setName(author.getName());
        dto.setBooks(author.getBooks().stream().map(Book::getTitle).collect(Collectors.toSet()));
        return dto;
    }
}

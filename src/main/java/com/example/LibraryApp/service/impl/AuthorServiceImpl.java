package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.exception.DuplicateResourceException;
import com.example.LibraryApp.exception.ResourceNotFoundException;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.repository.CustomAuthorRepository;
import com.example.LibraryApp.repository.CustomBookRepository;
import com.example.LibraryApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CustomAuthorRepository customAuthorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, CustomAuthorRepository customAuthorRepository) {

        this.authorRepository = authorRepository;
        this.customAuthorRepository = customAuthorRepository;
    }

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return new AuthorDto(
                author.getAuthor_id(),
                author.getName(),
                author.getBooks().stream()
                        .map(Book::getTitle)
                        .collect(Collectors.toList()));
    }

    public List<AuthorDto> getAuthorByName(String name) {
        if (!customAuthorRepository.existsByName(name)) {
            throw new ResourceNotFoundException("Author not found");
        }
        return customAuthorRepository.getAuthorByName(name).stream()
                .map(author -> new AuthorDto(
                        author.getAuthor_id(),
                        author.getName(),
                        author.getBooks().stream()
                                .map(Book::getTitle)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    };

    @Override
    public Author createAuthor(Author author) {
        if (customAuthorRepository.existsByName(author.getName())) {
            throw new DuplicateResourceException("Author already exists");
        }
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found");
        }

        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        if (author.getName() != null) {
            existingAuthor.setName(author.getName());
        }

        if (author.getBooks() != null) {
            existingAuthor.getBooks().clear();
            existingAuthor.getBooks().addAll(author.getBooks());
        }

        return authorRepository.save(existingAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found");
        }
        authorRepository.deleteById(id);
    }
}

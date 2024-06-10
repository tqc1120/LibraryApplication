package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.entity.Author;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        if (authorRepository.existsById(id)) {
            author.setAuthor_id(id);
            return authorRepository.save(author);
        } else {
            return null;
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}

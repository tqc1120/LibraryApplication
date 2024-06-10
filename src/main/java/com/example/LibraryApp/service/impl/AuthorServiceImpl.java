package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.CustomAuthorRepository;
import com.example.LibraryApp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        if (authorRepository.existsById(id)) {
            Author author = authorRepository.findById(id).orElse(null);
            return new AuthorDto(
                    author.getAuthor_id(),
                    author.getName(),
                    author.getBooks().stream()
                            .map(Book::getTitle)
                            .collect(Collectors.toList()));
        }
        return null;
    }

    public List<AuthorDto> getAuthorByName(String name) {
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

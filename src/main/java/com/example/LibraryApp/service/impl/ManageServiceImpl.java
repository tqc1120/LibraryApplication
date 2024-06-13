package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.AuthorDto;
import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.domain.entity.BookAuthor;
import com.example.LibraryApp.domain.entity.BookAuthorId;
import com.example.LibraryApp.exception.DuplicateResourceException;
import com.example.LibraryApp.exception.ResourceNotFoundException;
import com.example.LibraryApp.repository.AuthorRepository;
import com.example.LibraryApp.repository.BookAuthorRepository;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ManageServiceImpl implements ManageService {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookAuthorRepository bookAuthorRepository;

    @Autowired
    public ManageServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository, BookAuthorRepository bookAuthorRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.bookAuthorRepository = bookAuthorRepository;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        if (authorRepository.getAuthorsByName(authorDto.getName()).size() > 0) {
            throw new DuplicateResourceException("Author with name " + authorDto.getName() + " already exists");
        }

        Author author = new Author();

        author.setName(authorDto.getName());
        author = authorRepository.save(author);

        if (authorDto.getBooks() != null && !authorDto.getBooks().isEmpty()) {
            Set<BookAuthor> bookAuthors = new HashSet<>();
            for (String bookTitle : authorDto.getBooks()) {
                Optional<Book> optionalBook = bookRepository.findByTitle(bookTitle);
                Book book = optionalBook.orElseGet(() -> {
                    Book newBook = new Book();
                    newBook.setTitle(bookTitle);
                    return bookRepository.save(newBook);
                });

                BookAuthor bookAuthor = new BookAuthor();
                bookAuthor.setId(new BookAuthorId(author.getAuthor_id(), book.getBook_id()));
                bookAuthor.setAuthor(author);
                bookAuthor.setBook(book);
                bookAuthors.add(bookAuthor);
            }
            bookAuthorRepository.saveAll(bookAuthors);
            author.setBookAuthors(bookAuthors);
        }

        return mapToAuthorDto(author);
    }

    @Override
    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.deleteById(book.getBook_id());

        Set<BookAuthor> bookAuthors = book.getBookAuthors();
        bookAuthorRepository.deleteAllInBatch(bookAuthors);
    }

    private AuthorDto mapToAuthorDto(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.setAuthorId(author.getAuthor_id());
        dto.setName(author.getName());
        dto.setBooks(author.getBookAuthors().stream().map(bookAuthor -> bookAuthor.getBook().getTitle()).collect(Collectors.toSet()));
        return dto;
    }
}

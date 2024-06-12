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
import com.example.LibraryApp.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@Service
//@Transactional
//public class BookServiceImpl implements BookService {
//    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
//
//    private final AuthorRepository authorRepository;
//    private final BookRepository bookRepository;
//    private final CustomBookRepository customBookRepository;
//    private final CustomAuthorRepository customAuthorRepository;
//
//    @Autowired
//    public BookServiceImpl(AuthorRepository authorRepository, CustomAuthorRepository customAuthorRepository, BookRepository bookRepository, CustomBookRepository customBookRepository) {
//        this.authorRepository = authorRepository;
//        this.bookRepository = bookRepository;
//        this.customBookRepository = customBookRepository;
//        this.customAuthorRepository = customAuthorRepository;
//    }
//
//    @Override
//    public BookDto getBookById(Long id) {
//        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
//        return convertToDto(book);
//    }
//
//    @Override
//    public List<BookDto> getBooksByTitle(String title) {
//        if (!customBookRepository.existsByTitle(title)) {
//            throw new ResourceNotFoundException("Book not found");
//        }
//        return customBookRepository.getBooksByTitle(title).stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public BookDto createBook(BookDto bookDto) {
//        if (customBookRepository.existsByTitle(bookDto.getTitle())) {
//            throw new DuplicateResourceException("Book already exists");
//        }
//        Book book = convertToEntity(bookDto);
//        Book savedBook = bookRepository.save(book);
//        return convertToDto(savedBook);
//    }
//
//    @Override
//    public BookDto updateBook(Long id, BookDto bookDto) {
//        Book existingBook = bookRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
//
//        logger.debug("Received book details: {}", bookDto);
//        logger.debug("Authors received: {}", bookDto.getAuthors());
//
//        if (bookDto.getTitle() != null) {
//            existingBook.setTitle(bookDto.getTitle());
//            logger.debug("Updated book's title to: {}", bookDto.getTitle());
//        }
//
//        if (bookDto.getAuthors() != null) {
//            Set<Author> authors = new HashSet<>();
//            for (AuthorDto authorDto : bookDto.getAuthors()) {
//                Author existingAuthor = authorRepository.findById(authorDto.getAuthorId())
//                        .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
//                authors.add(existingAuthor);
//                logger.debug("Added existing author to book: {}", existingAuthor);
//            }
//            existingBook.setAuthors(authors);
//
//            for (Author author : authors) {
//                if (!author.getBooks().contains(existingBook)) {
//                    author.getBooks().add(existingBook);
//                    authorRepository.save(author);
//                    logger.debug("Updated author's books and saved: {}", author);
//                }
//            }
//            logger.debug("Updated authors for book: {}", existingBook.getAuthors());
//        } else {
//            logger.debug("No authors provided for update.");
//        }
//        Book updatedBook = bookRepository.save(existingBook);
//        logger.debug("Updated book: {}", updatedBook);
//        return convertToDto(updatedBook);
//    }
//
//    @Override
//    public void deleteBook(Long id) {
//        if (!bookRepository.existsById(id)) {
//            throw new ResourceNotFoundException("Book not found");
//        }
//        bookRepository.deleteById(id);
//    }
//
//    private BookDto convertToDto(Book book) {
//        BookDto bookDto = new BookDto();
//        bookDto.setBookId(book.getBook_id());
//        bookDto.setTitle(book.getTitle());
//        bookDto.setAuthors(book.getAuthors().stream().map(this::convertToDto).collect(Collectors.toList()));
//        return bookDto;
//    }
//
//    private AuthorDto convertToDto(Author author) {
//        AuthorDto authorDto = new AuthorDto();
//        authorDto.setAuthorId(author.getAuthor_id());
//        authorDto.setName(author.getName());
//        return authorDto;
//    }
//
//    private Book convertToEntity(BookDto bookDto) {
//        Book book = new Book();
//        book.setBook_id(bookDto.getBookId());
//        book.setTitle(bookDto.getTitle());
//        book.setAuthors(bookDto.getAuthors().stream().map(this::convertToEntity).collect(Collectors.toSet()));
//        return book;
//    }
//
//    private Author convertToEntity(AuthorDto authorDto) {
//        Author author = new Author();
//        author.setAuthor_id(authorDto.getAuthorId());
//        author.setName(authorDto.getName());
//        return author;
//    }
}

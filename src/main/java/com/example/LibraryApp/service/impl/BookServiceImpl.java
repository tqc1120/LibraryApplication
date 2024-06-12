package com.example.LibraryApp.service.impl;

import com.example.LibraryApp.domain.dto.BookDto;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.exception.ResourceNotFoundException;
import com.example.LibraryApp.repository.BookRepository;
import com.example.LibraryApp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return mapToBookDto(book);
    }

    @Override
    public List<BookDto> getBooksByTitle(String title) {
        Optional<Book> books = bookRepository.findByTitle(title);
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No books found with the title: " + title);
        }
        return books.stream().map(this::mapToBookDto).collect(Collectors.toList());
    }

    //mappers
    private BookDto mapToBookDto(Book book) {
        BookDto dto = new BookDto();
        dto.setBookId(book.getBook_id());
        dto.setTitle(book.getTitle());
        dto.setAuthors(book.getBookAuthors().stream().map(bookAuthor -> bookAuthor.getAuthor().getName()).collect(Collectors.toSet()));
        return dto;
    }

}

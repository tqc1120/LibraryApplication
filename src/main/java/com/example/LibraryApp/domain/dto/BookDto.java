package com.example.LibraryApp.domain.dto;

import java.util.Set;

public class BookDto {
    private Long bookId;
    private String title;
    private Set<String> authors;

    public BookDto() {
    }

    public BookDto(Long bookId, String title, Set<String> authors) {
        this.bookId = bookId;
        this.title = title;
        this.authors = authors;
    }

    public BookDto(String title, Set<String> authors) {
        this.title = title;
        this.authors = authors;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<String> authors) {
        this.authors = authors;
    }
}

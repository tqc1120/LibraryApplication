package com.example.LibraryApp.domain.dto;

import java.util.List;

public class BookDto {
    private Long bookId;
    private String title;
    private List<String> authorNames;

    public BookDto() {
    }

    public BookDto(Long bookId, String title, List<String> authorNames) {
        this.bookId = bookId;
        this.title = title;
        this.authorNames = authorNames;
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

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }
}

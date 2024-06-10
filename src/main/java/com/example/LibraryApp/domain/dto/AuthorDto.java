package com.example.LibraryApp.domain.dto;

import java.util.List;

public class AuthorDto {
    private Long authorId;
    private String name;
    private List<String> bookTitles;

    public AuthorDto() {
    }

    public AuthorDto(Long authorId, String name, List<String> bookTitles) {
        this.authorId = authorId;
        this.name = name;
        this.bookTitles = bookTitles;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBookTitles() {
        return bookTitles;
    }

    public void setBookTitles(List<String> bookTitles) {
        this.bookTitles = bookTitles;
    }
}


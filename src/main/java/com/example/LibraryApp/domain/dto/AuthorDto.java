package com.example.LibraryApp.domain.dto;

import java.util.Set;

public class AuthorDto {
    private Long authorId;
    private String name;
    private Set<String> books;

    public AuthorDto() {
    }

    public AuthorDto(String name, Set<String> books) {
        this.name = name;
        this.books = books;
    }

    public AuthorDto(Long authorId, String name, Set<String> books) {
        this.authorId = authorId;
        this.name = name;
        this.books = books;
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

    public Set<String> getBooks() {
        return books;
    }

    public void setBooks(Set<String> books) {
        this.books = books;
    }
}


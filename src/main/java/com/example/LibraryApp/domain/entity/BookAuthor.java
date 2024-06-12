package com.example.LibraryApp.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "book_authors")
public class BookAuthor {
    @EmbeddedId
    BookAuthorId id;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Author author;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    public BookAuthorId getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public Book getBook() {
        return book;
    }
}

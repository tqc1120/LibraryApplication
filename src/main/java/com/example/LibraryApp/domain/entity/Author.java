package com.example.LibraryApp.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long author_id;
//
//    @Column(nullable = false)
//    private String name;
//
////    @JsonIgnore
//    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Book> books = new HashSet<>();
//
//    public Long getAuthor_id() {
//        return author_id;
//    }
//
//    public void setAuthor_id(Long author_id) {
//        this.author_id = author_id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Set<Book> getBooks() {
//        return books;
//    }
//
//    public void setBooks(Set<Book> books) {
//        this.books = books;
//    }
}
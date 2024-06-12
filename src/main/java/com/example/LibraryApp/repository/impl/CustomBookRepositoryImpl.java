package com.example.LibraryApp.repository.impl;

import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.repository.CustomBookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomBookRepositoryImpl implements CustomBookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> getBooksByTitle(String title) {
        String jpql = "SELECT b FROM Book b WHERE b.title = :title";
        TypedQuery<Book> query = entityManager.createQuery(jpql, Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }
}

package com.example.LibraryApp.repository.impl;


import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.repository.CustomBookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomBookRepositoryImpl implements CustomBookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> getBooksByTitle(String title) {
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT book FROM Book book WHERE book.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }
}

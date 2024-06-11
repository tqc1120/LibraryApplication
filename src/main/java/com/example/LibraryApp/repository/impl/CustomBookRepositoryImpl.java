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
        TypedQuery<Book> query = entityManager.createQuery(
                "SELECT book FROM Book book WHERE book.title = :title", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public boolean existsByTitle(String title) {
        String queryStr = "SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM Book b WHERE b.title = :title";
        Query query = entityManager.createQuery(queryStr);
        query.setParameter("title", title);
        return (Boolean) query.getSingleResult();
    }
}

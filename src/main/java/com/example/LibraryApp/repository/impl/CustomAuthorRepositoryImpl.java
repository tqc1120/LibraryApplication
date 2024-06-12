package com.example.LibraryApp.repository.impl;

import com.example.LibraryApp.domain.entity.Author;
import com.example.LibraryApp.domain.entity.Book;
import com.example.LibraryApp.repository.CustomAuthorRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomAuthorRepositoryImpl implements CustomAuthorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> getAuthorsByName(String name) {
        String jpql = "SELECT a FROM Author a  WHERE a.name = :name";
        TypedQuery<Author> query = entityManager.createQuery(jpql, Author.class);
        query.setParameter("name", name);
        return query.getResultList();
    }
}

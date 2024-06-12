//package com.example.LibraryApp.repository.impl;
//
//import com.example.LibraryApp.domain.entity.Author;
//import com.example.LibraryApp.domain.entity.Book;
//import com.example.LibraryApp.repository.CustomAuthorRepository;
//import jakarta.persistence.*;
//import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public class CustomAuthorRepositoryImpl implements CustomAuthorRepository {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Author> getAuthorByName(String name) {
//        TypedQuery<Author> query = entityManager.createQuery(
//                "SELECT author FROM Author author WHERE author.name = :name", Author.class);
//        query.setParameter("name", name);
//        return query.getResultList();
//    }
//
//    @Override
//    public boolean existsByName(String name) {
//        String queryStr = "SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Author a WHERE a.name = :name";
//        Query query = entityManager.createQuery(queryStr);
//        query.setParameter("name", name);
//        return (Boolean) query.getSingleResult();
//    }
//}

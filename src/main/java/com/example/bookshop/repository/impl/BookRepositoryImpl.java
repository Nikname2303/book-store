package com.example.bookshop.repository.impl;

import com.example.bookshop.exception.DataProcessingException;
import com.example.bookshop.exception.EntityNotFoundException;
import com.example.bookshop.model.Book;
import com.example.bookshop.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookRepositoryImpl implements BookRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Book save(Book book) {
        EntityManager session = null;
        EntityTransaction transaction = null;
        try {
            session = entityManagerFactory.createEntityManager();
            transaction = session.getTransaction();
            session.merge(book);
            transaction.commit();
            return book;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save book to DB" + book, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Book> findAll() {
        try (EntityManager session = entityManagerFactory.createEntityManager()) {
            TypedQuery<Book> fromBook = session.createQuery("FROM Book", Book.class);
            return fromBook.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get books ", e);
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Book book = entityManager.find(Book.class, id);
            return Optional.ofNullable(book);
        } catch (Exception e) {
            throw new EntityNotFoundException("Can't find book with id: " + id);
        }
    }
}

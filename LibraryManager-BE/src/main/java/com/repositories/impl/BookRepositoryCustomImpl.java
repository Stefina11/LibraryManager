package com.repositories.impl;

import com.entities.Book;
import com.repositories.BookRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final EntityManager em;

    public BookRepositoryCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Book> searchByQuery(String query) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> root = cq.from(Book.class);

        String q = "%" + query.toLowerCase() + "%";
        Predicate byTitle    = cb.like(cb.lower(root.get("title")),    q);
        Predicate byAuthor   = cb.like(cb.lower(root.get("author")),   q);
        Predicate byCategory = cb.like(cb.lower(root.get("category")), q);

        cq.where(cb.or(byTitle, byAuthor, byCategory));

        return em.createQuery(cq).getResultList();
    }
}

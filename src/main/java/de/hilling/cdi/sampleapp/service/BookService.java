package de.hilling.cdi.sampleapp.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.hilling.cdi.sampleapp.jpa.Book;

@Stateless
public class BookService {
    private static final Logger LOG = Logger.getLogger(BookService.class.getName());

    @PersistenceContext
    private EntityManager entityManager;

    public BookService() {
        LOG.info("created");
    }

    public List<Book> getAllBooks() {
        return entityManager.createNamedQuery("allBooks", Book.class).getResultList();
    }

    public Book getBookById(long id) {
        return entityManager.find(Book.class, id);
    }

    public long saveBook(Book book) {
        entityManager.persist(book);
        return book.getId();
    }
}

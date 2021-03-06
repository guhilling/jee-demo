package de.hilling.cdi.sampleapp.rest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import de.hilling.cdi.sampleapp.jpa.Book;
import de.hilling.cdi.sampleapp.testsupport.BookBaseTest;
import de.hilling.junit.cdi.CdiTestJunitExtension;

@ExtendWith(CdiTestJunitExtension.class)
class BookResourceIntegrationTest extends BookBaseTest {

    @Inject
    private BookResource bookResource;

    @Inject
    private EntityManager entityManager;

    @Test
    void loadNoBooks() {
        assertThat(bookResource.getAllBooks(), empty());
    }

    @Test
    void storeBook() {
        long firstId = bookResource.addBook(firstBook);
        long secondId = bookResource.addBook(secondBook);
        assertNotEquals(firstId, secondId);
    }

    @Test
    void storeAndLoadBook() {
        long id = bookResource.addBook(firstBook);
        entityManager.flush();
        entityManager.clear();
        Book firstReloaded = bookResource.getBookById(id);
        assertEquals(firstReloaded.getTitle(), firstBook.getTitle());
    }
}
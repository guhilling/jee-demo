package de.hilling.cdi.sampleapp.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;

import de.hilling.cdi.sampleapp.jpa.Book;
import de.hilling.cdi.sampleapp.service.BookService;

/**
 * REST API for {@link Book}s.
 */
@Path("/books")
@RequestScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private static final Logger LOG = Logger.getLogger("de.hilling.books");

    @Inject
    private BookService bookService;

    /**
     * Get all books.
     * @return list of books in JSON format.
     */
    @Path("/")
    @GET
    @Counted(name = "allBooks", description = "Counts the number of allBooks calls")
    public List<Book> getAllBooks() {
        LOG.info("getAllBooks");
        return bookService.getAllBooks();
    }

    /**
     * Load book by id.
     * @param id primary key.
     * @return requested book or 404.
     */
    @Path("/byId/{id}")
    @GET
    @Counted(name = "getBookById", description = "Counts the number of getBookById calls")
    public Book getBookById(@PathParam("id") Long id) {
        return bookService.getBookById(id);
    }

    /**
     * Create new book
     * @param book new book.
     * @return primary key of newly created data set.
     */
    @Path("/")
    @POST
    @Counted(name = "createBook", description = "Counts the number of createBook calls")
    public long addBook(Book book) {
        return bookService.saveBook(book);
    }


}

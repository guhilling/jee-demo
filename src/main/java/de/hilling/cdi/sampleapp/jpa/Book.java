package de.hilling.cdi.sampleapp.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "allBooks", query = "SELECT b FROM Book b")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long   id;
    private String title;

    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title +" id: " + id + "";
    }
}

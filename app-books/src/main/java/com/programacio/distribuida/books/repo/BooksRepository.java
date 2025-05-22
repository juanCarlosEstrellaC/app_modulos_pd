package com.programacio.distribuida.books.repo;

import com.programacio.distribuida.books.db.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class BooksRepository implements PanacheRepositoryBase<Book, String> {

    public Optional<Book> findByID(String isbn) {
        return findByIdOptional(isbn);
    }

    public Optional<Book> findByTitle(String title) {
        return find("title", title).firstResultOptional();
    }

    public List<Book> findByPriceGreaterThan(BigDecimal price) {
        return list("price > ?1", price);
    }

    public Optional<Book> findByInventorySoldGreaterThan(Integer sold) {
        return find("inventory.sold > ?1", sold).firstResultOptional();
    }

    public Optional<Book> update(String isbn, Book book) {
        Optional<Book> obj = findByIdOptional(isbn);
        if (obj.isEmpty()) {
            return Optional.empty();
        }
        Book bookObj = obj.get();
        bookObj.setTitle(book.getTitle());
        bookObj.setPrice(book.getPrice());
        bookObj.setVersion(book.getVersion());
        return Optional.of(bookObj);
    }

}
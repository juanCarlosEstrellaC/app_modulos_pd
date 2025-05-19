package com.progra.distribuida.books.repo;

import com.progra.distribuida.books.db.Book;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class BooksRepository implements PanacheRepositoryBase<Book, Integer> {

    public Optional<Book> findByIdOptional(String isbn) {
        return this.find("select o.Book from BookAuthor o where o.id.bookIsbn = ?1", isbn).firstResultOptional();
    }
    public Optional<Book> update(Integer id, Book Book) {
        var obj = this.findByIdOptional(id);
        if (obj.isEmpty()) {
            return Optional.empty();
        }

        var authorObj = obj.get();
        //authorObj.setName(Book.getName());
        authorObj.setVersion(Book.getVersion());
        return Optional.of(authorObj);
    }
}

package com.progra.distribuida.authors.repo;

import com.progra.distribuida.authors.db.Author;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;

@ApplicationScoped
public class AuthorRepository implements PanacheRepositoryBase<Author, Integer> {

    public Optional<Author> update(Integer id, Author author) {
        var obj = this.findByIdOptional(id);
        if (obj.isEmpty()) {
            return Optional.empty();
        }

        var authorObj = obj.get();
        authorObj.setName(author.getName());
        authorObj.setVersion(author.getVersion());
        return Optional.of(authorObj);
    }
}

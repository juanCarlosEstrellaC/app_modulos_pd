package com.progra.distribuida.books.rest;

import com.progra.distribuida.books.db.Book;
import com.progra.distribuida.books.dtos.AuthorDto;
import com.progra.distribuida.books.dtos.BookDto;
import com.progra.distribuida.books.repo.BooksRepository;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Stream;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class BookRest {

    @Inject
    BooksRepository booksRepository;

    @GET
    @Path("/{isbn}")
    public Response findByIsbn(@PathParam("isbn") String isbn) {
    /*    return booksRepository.findByIdOptional(isbn)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();*/

        BookDto ret = new BookDto();
        //1.Buscar el libro
        var obj = booksRepository.findByIdOptional(isbn);
        if (obj.isEmpty()) {
            // Si no se encuentra el libro, se devuelve un error 404
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        var book = obj.get();
        ret.setIsbn(book.getIsbn());
        ret.setTitle(book.getTitle());
        ret.setPrice(book.getPrice());

        /*2. Buscar el inventario
        var inventary = book.getInventary();
        if (inventary != null) {
            ret.setInventarySold(inventary.getSold());
            ret.setInventarySupplied(inventary.getSupplied());
        }*/


        // 3. Buscar los autores
        var client = ClientBuilder.newClient();
        AuthorDto[] authors = client.target("http://localhost:8080")
                .path("/authors/find/{isbn}")
                .resolveTemplate("isbn", isbn)
                .request(MediaType.APPLICATION_JSON)
                .get(AuthorDto[].class);

        ret.setAuthors(
                Stream.of(authors)
                        .map(AuthorDto::getName)
                        .toList()
        );

        return Response.ok(ret).build();
    }

    @GET
    public List<Book> findAll() {
        return booksRepository.listAll();
    }
}

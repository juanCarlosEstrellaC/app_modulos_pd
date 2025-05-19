package com.progra.distribuida.books.rest;

import com.progra.distribuida.books.db.Author;
import com.progra.distribuida.books.repo.AuthorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class AuthorRest {

    @Inject
    @ConfigProperty(name = "quarkus.http.port")
    Integer appPort;

    @Inject
    AuthorRepository authorRepository;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var obj = authorRepository.findByIdOptional(id);

        if (obj.isEmpty()) {
            // Si no se encuentra el autor, se devuelve un error 404
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(obj.get()).build();
    }

    @GET
    public List<Author> findAll() {
        return authorRepository.findAll().list();
    }


    @GET
    @Path("/find/{isbn}")
    public List<Author> findByBook(@PathParam("isbn") String isbn) {
        var ret = authorRepository.findByBook(isbn);

        Config config = ConfigProvider.getConfig();
        config.getConfigSources().forEach(obj -> {
            System.out.printf("%d -> %s\n", obj.getOrdinal(), obj.getName());
        });

        var puerto = config.getValue("quarkus.http.port", Integer.class);

        return ret.stream().map(obj -> {
            String newName = String.format("%s (%d)", obj.getName(), puerto);
            obj.setName(newName);
            return obj;
        }).toList();
    }

    @POST
    public Response create(Author author) {
        try {
            authorRepository.persist(author);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Author author) {
        var obj = authorRepository.findByIdOptional(id);
        if (obj.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        authorRepository.update(id, author);
        return Response.ok(obj.get()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        var obj = authorRepository.findByIdOptional(id);
        if (obj.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        authorRepository.deleteById(id);
        return Response.ok(obj.get()).build();
    }
}

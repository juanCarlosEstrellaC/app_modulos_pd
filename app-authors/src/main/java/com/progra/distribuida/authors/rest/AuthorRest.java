package com.progra.distribuida.authors.rest;

import com.progra.distribuida.authors.db.Author;
import com.progra.distribuida.authors.repo.AuthorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AuthorRest {

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

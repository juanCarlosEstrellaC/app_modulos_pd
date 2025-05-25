package com.programacio.distribuida.books.clients;

import com.programacio.distribuida.books.dtos.AuthorDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AuthorRestClient {

    @GET
    @Path("/find/{isbn}")
    List<AuthorDto> findByBook(@PathParam("isbn") String isbn);

}

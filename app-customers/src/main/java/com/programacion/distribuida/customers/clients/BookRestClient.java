package com.programacion.distribuida.customers.clients;

import com.programacion.distribuida.customers.dto.BookDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri = "stork://books-api")
public interface BookRestClient {
    @GET
    @Path("/{isbn}")
    BookDto findByBook(@PathParam("isbn") String isbn);
}

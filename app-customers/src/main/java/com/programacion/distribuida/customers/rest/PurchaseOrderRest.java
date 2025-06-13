package com.programacion.distribuida.customers.rest;

import com.programacion.distribuida.customers.clients.BookRestClient;
import com.programacion.distribuida.customers.db.PurchaseOrder;
import com.programacion.distribuida.customers.dto.PurchaseOrderDto;
import com.programacion.distribuida.customers.repo.PurchaseOrderRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.modelmapper.ModelMapper;

import java.util.List;

@Path("/orders")
@Transactional
public class PurchaseOrderRest {

    @Inject
    ModelMapper mapper;

    @Inject
    PurchaseOrderRepository repository;

    @Inject
    @RestClient
    BookRestClient bookRestClient;

    private PurchaseOrderDto map(PurchaseOrder source) {
        var dto = mapper.map(source, PurchaseOrderDto.class);

        dto.getLineItems()
                .stream()
                .forEach(item -> {
                    var book = bookRestClient.findByBook(item.getIsbn());

                    item.setIsbn(book.getIsbn());
                    item.setTitle(book.getTitle());
                    item.setPrice(book.getPrice());

                    item.setAuthors(book.getAuthors());
                });

        return dto;
    }

    @GET
    @Path("/customer/{customerId}")
    public List<PurchaseOrderDto> orders(@PathParam("customerId") Integer customerId) {

        return repository.findByCustomerId(customerId)
                .stream()
                .map(this::map)
                .toList();
    }

    @GET
    @Path("/{orderId}")
    public Response orderDetail(@PathParam("orderId") Integer orderId) {

        return repository.findByIdOptional(orderId)
                .map(this::map)
                .map(Response::ok)
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }
}

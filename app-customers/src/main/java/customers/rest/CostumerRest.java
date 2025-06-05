package customers.rest;


import customers.clients.AuthorRestClient;
import customers.repo.CustomersRepository;
import customers.repo.PurchaseOrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.modelmapper.ModelMapper;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class CostumerRest {

    @Inject
    CustomersRepository repository;

    @Inject
    PurchaseOrderRepository repositoryPurchaseOrder;

    @Inject
    ModelMapper mapper;

    @Inject
    @RestClient
    AuthorRestClient client;

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var obj = repository.findByIdOptional(id);
        if (obj.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(obj.get()).build();
    }

/*    @GET
    @Path("/{id}")
    @Transactional
    public Response findById(@PathParam("id") Integer id) {
        var obj = repositoryPurchaseOrder.findByIdOptional(id);

        if (obj.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(obj.get()).build();
    }*/





}




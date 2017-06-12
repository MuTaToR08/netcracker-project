package com.netcraker.project.bd.server.services.api.client;

import com.netcraker.project.bd.server.model.client.ModelOrder;
import com.netcraker.project.bd.shared.objects.client.Order;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("order")
public class ServiceOrder implements RestService {

    @Context
    private ServletContext context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAll()
    {
        return new ModelOrder(context).getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Order getById(@PathParam("id") int id)
    {
        return new ModelOrder(context).getById(id);
    }
}

package com.netcraker.project.bd.server.services.api.location;

import com.netcraker.project.bd.server.model.location.ModelStock;
import com.netcraker.project.bd.shared.objects.location.Stock;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("stock")
public class ServiceStock implements RestService {

    @Context
    ServletContext context;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stock> getAll()
    {
        return new ModelStock(context).getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Stock getById(@PathParam("id") int id)
    {
        return new ModelStock(context).getById(id);
    }
}

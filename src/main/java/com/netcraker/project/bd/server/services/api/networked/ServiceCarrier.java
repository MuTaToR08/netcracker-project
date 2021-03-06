package com.netcraker.project.bd.server.services.api.networked;

import com.netcraker.project.bd.server.model.networked.ModelCarrier;
import com.netcraker.project.bd.shared.objects.networked.Carrier;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("carrier")
public class ServiceCarrier implements RestService {

    @Context
    private ServletContext context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Carrier> getAll()
    {
        return new ModelCarrier(context).getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Carrier getById(@PathParam("id") int id)
    {
        return new ModelCarrier((context)).getById(id);
    }
}

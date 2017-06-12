package com.netcraker.project.bd.server.services.api.location;

import com.netcraker.project.bd.server.model.location.ModelStand;
import com.netcraker.project.bd.shared.objects.location.Stand;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("stand")
public class ServiceStand implements RestService {

    @Context
    private ServletContext  context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Stand> getAll()
    {
        return new ModelStand(context).getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Stand getById(@PathParam("id") int id)
    {
        return new ModelStand(context).getById(id);
    }

}

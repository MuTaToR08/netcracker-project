package com.netcraker.project.bd.server.services.api.networked;


import com.netcraker.project.bd.server.model.networked.ModelPort;
import com.netcraker.project.bd.shared.objects.networked.Carrier;
import com.netcraker.project.bd.shared.objects.networked.Port;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("port")
public class ServicePort implements RestService{

    @Context
    ServletContext context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Port> getAll()
    {
        return new ModelPort(context).getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Port getByID(@PathParam("id") int id)
    {
        return new ModelPort(context).getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/carrier")
    public Carrier getCarrier(@PathParam("id") int id)
    {
        return new ModelPort(context).getCarrier(id);
    }
}

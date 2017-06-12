package com.netcraker.project.bd.server.services.api.location;

import com.netcraker.project.bd.server.model.location.ModelFloor;
import com.netcraker.project.bd.shared.objects.location.Floor;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("floor")
public class ServiceFloor implements RestService {

    @Context
    private ServletContext context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Floor> getAll()
    {
        return new ModelFloor(context).getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Floor getById(@PathParam("id") int id)
    {
        return new ModelFloor(context).getById(id);
    }
}

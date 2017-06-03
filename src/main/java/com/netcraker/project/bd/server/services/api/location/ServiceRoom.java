package com.netcraker.project.bd.server.services.api.location;

import com.netcraker.project.bd.server.model.location.ModelRoom;
import com.netcraker.project.bd.shared.objects.location.Room;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("room")
public class ServiceRoom implements RestService {


    @Context
    ServletContext context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAll()
    {
        return new ModelRoom(context).getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Room getById(@PathParam("id") int id)
    {
        return new ModelRoom(context).getById(id);
    }

}

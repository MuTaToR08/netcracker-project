package com.netcraker.project.bd.server.services.api.networked;

import com.netcraker.project.bd.server.model.networked.ModelRouter;
import com.netcraker.project.bd.shared.objects.networked.Router;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("router")
public class ServiceRouter implements RestService{
    @Context
    ServletContext context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Router> getAll()
    {
        return new ModelRouter(context).getAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Router getById(@PathParam("id") int id)
    {
     return new ModelRouter(context).getById(id);
    }
}

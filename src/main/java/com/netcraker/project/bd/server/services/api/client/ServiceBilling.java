package com.netcraker.project.bd.server.services.api.client;

import com.netcraker.project.bd.server.model.client.ModelBilling;
import com.netcraker.project.bd.shared.objects.client.Billing;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.util.List;

@Path("billing")
public class ServiceBilling implements RestService {

    @Context
    private ServletContext context;

    @GET
    public List<Billing> getAll()
    {
        return new ModelBilling(context).getAll();
    }

    @GET
    @Path("{id}")
    public Billing getById(@PathParam("id") int id)
    {
        return new ModelBilling(context).getById(id);
    }
}

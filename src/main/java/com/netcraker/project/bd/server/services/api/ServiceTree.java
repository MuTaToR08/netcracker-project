package com.netcraker.project.bd.server.services.api;

import com.netcraker.project.bd.server.model.FactoryObjectsBD;
import com.netcraker.project.bd.shared.containers.TreeElement;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("tree")
public class ServiceTree implements RestService {

    @Context
    private ServletContext context;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("parent/{id}/")
    public List<Object> getParents(@PathParam("id") int id)
    {
        return (List<Object>) new FactoryObjectsBD(context).getParent(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("children/{id}")
    public List<Object> getChildes(@PathParam("id") int id)
    {
        return (List<Object>) new FactoryObjectsBD(context).getChields(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("children/")
    public List<Object> getChildes()
    {
        return getChildes(0);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("reference/{id}")
    public List<TreeElement> getReferences(@PathParam("id") int id)
    {
        return  new FactoryObjectsBD(context).getReferences(id);
    }

}

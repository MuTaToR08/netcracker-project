package com.netcraker.project.bd.client.api;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/service/api/tree")
public interface TreeApi extends RestService {


    @GET
    @Path("parent/{id}/")
    String getParents(@PathParam("id") int id, MethodCallback<List<Object>> objects);

    @GET
    @Path("children/{id}")
    String getChilds(@PathParam("id") int id, MethodCallback<List<Object>> objectsBD);

    @GET
    @Path("reference/{id}")
    String getReference(@PathParam("id") int id, MethodCallback<List<Object>> objectsBD);

}

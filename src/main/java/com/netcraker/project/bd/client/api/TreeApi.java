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
    void getParents(@PathParam("id") int id, MethodCallback<List<Object>> objects);

    @GET
    @Path("children/{id}")
    void getChilds(@PathParam("id") int id, MethodCallback<List<Object>> objectsBD);

    @GET
    @Path("reference/{id}")
    void getReference(@PathParam("id") int id, MethodCallback<List<Object>> objectsBD);

}

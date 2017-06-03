package com.netcraker.project.bd.client.api;

import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/service/api/tree")
public interface TreeApi extends RestService {

    @GET
    @Path("parent/{id}/")
    List<? super ObjectBD> getParents(@PathParam("id") int id);

   /* @GET
    @Path("childen/{id:0}")
    public List<? super ObjectBD> getChilds(@PathParam("id") int id, MethodCallback<List<? super ObjectBD>> callback);
*/
}

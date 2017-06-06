package com.netcraker.project.bd.client.api;

import com.google.gwt.dev.util.collect.HashMap;
import com.netcraker.project.bd.shared.objects.ObjectType;
import com.netcraker.project.bd.shared.objects.Status;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Map;

@Path("/service/api/static")
public interface StaticObjectApi extends RestService {

    @GET
    @Path("status")
    void getAllStatus(MethodCallback<HashMap<Integer,Status>> status);

    @GET
    @Path("types")
    void getAllObjectTypes(MethodCallback<Map<Integer,ObjectType>> types);


}

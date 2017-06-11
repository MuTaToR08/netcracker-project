package com.netcraker.project.bd.client.api.networked;

import com.netcraker.project.bd.shared.objects.networked.Carrier;
import com.netcraker.project.bd.shared.objects.networked.Port;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("service/api/port")
public interface PortApi extends RestService{

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/carrier")
    void getConnectCarrier(@PathParam("id") int portID, MethodCallback<Carrier> obj);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    void getAll(MethodCallback<List<Port>> list);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    void getPort(@PathParam("id") int id,MethodCallback<Port> obj);
}

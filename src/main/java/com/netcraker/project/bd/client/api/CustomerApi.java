package com.netcraker.project.bd.client.api;


import com.netcraker.project.bd.shared.objects.CSI;
import com.netcraker.project.bd.shared.objects.Customer;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Map;

@Path("/service/api/customer")
public interface CustomerApi extends RestService {

    @GET
    @Path("{id}")
    void get(@PathParam("id") int id, MethodCallback<Customer> customer);

    @GET
    void getAll(MethodCallback<Map<Integer,Customer>> customers);

    @GET
    @Path("{id}/csi")
    void getCSICustomer(@PathParam("id") int id, MethodCallback<List<CSI>> listMethodCallback);

}

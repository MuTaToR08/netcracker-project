package com.netcraker.project.bd.client.api.client;

import com.netcraker.project.bd.shared.objects.client.Tariff;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/service/api/tariff")
public interface TariffApi extends RestService {
    @GET
    void getAll(MethodCallback<Map<Integer, Tariff>> method);
}

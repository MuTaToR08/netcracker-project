package com.netcraker.project.bd.server.services.api;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.server.Model.client.ModelTariff;
import com.netcraker.project.bd.shared.objects.Tariff;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@Path("tariff")
public class ServiceTariff implements RestService {

    final Map<Integer, Tariff> allTariff = new HashMap<>();
    static long cache = 0;
    @Context
    private ServletContext context;

    @GET
    @Path("{id}")
    public Tariff getId(@PathParam("id") int id)
    {
        if(( System.currentTimeMillis() / 1000L)-cache < 3600 && allTariff.containsKey(id))
            return allTariff.get(id);

        ModelTariff modelTariff = new ModelTariff(context);
        Tariff tariff = modelTariff.getById(id);


        return tariff;
    }



    @GET
    public Map<Integer, Tariff> getAll()
    {
        if(( System.currentTimeMillis() / 1000L)-cache < 3600)
            return allTariff;


        //SELECT * FROM TABLE(getobjects.tariff);
        try {
            Connection cn = ListenerContext.getDBOracle(context);

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("SELECT * FROM TABLE(getobjects.tariff);");

            while(rs.next()) {
                allTariff.put(rs.getInt("tariffId"), new Tariff(rs.getInt("tariffId"), rs.getString("tariffName"), rs.getInt("statusid")));
            }

            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        cache = System.currentTimeMillis()/1000L;
        return allTariff;
    }

}

package com.netcraker.project.bd.server.services.api;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.shared.objects.ObjectType;
import com.netcraker.project.bd.shared.objects.Status;
import org.apache.commons.collections.map.HashedMap;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

@Path("static")
public class ServiceStaticObject implements RestService {

    @Context
    private ServletContext context;

    public void setContext(ServletContext context) {
        this.context = context;
    }

    static protected Map<Integer,Status> statuses = null;
    static protected Map<Integer,ObjectType> objects = null;


    @GET
    @Path("types")
    @Produces("application/json")
    public Map<Integer,ObjectType> getAllObjects()
    {
        if(objects != null)
            return objects;
        objects  = new HashedMap();
        try {
            Connection cn = ListenerContext.getDBOracle(context);
            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery ("SELECT * FROM objects_type");
            while(rs.next()) {
                objects.put(rs.getInt("objects_type_id"),new ObjectType(rs.getInt("objects_type_id"),
                        rs.getString("name"),rs.getString("description"),rs.getInt("parent_type_id")));
            }

        }
        catch (Exception e) {}
        return objects;
    }

    @GET
    @Path("status")
    @Produces("application/json")
    public Map<Integer,Status> getAllStatus()
    {
        //if(statuses != null)
          //  return statuses;
        statuses = new HashedMap();
        try {
            Connection cn = ListenerContext.getDBOracle(context);
            if(cn.isClosed())
               throw new IOException("DateBase not ready");

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("SELECT * FROM LIST_DATA");
            while(rs.next()) {
                statuses.put(
                        rs.getInt("list_id"),
                        new Status(rs.getInt("list_id"),
                                rs.getInt("attribute_id"),
                                rs.getString("text")));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return statuses;
    }
}

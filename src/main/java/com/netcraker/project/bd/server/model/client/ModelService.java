package com.netcraker.project.bd.server.model.client;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.shared.objects.client.Service;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModelService {
    private ServletContext context;

    public ModelService(ServletContext context) {
        this.context = context;
    }

    public Service getById(int id)
    {
        Service service= null;
        try {
            Connection cn = ListenerContext.getDBOracle(context);

            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery ("SELECT * FROM TABLE(getobjects.services("+id+"))");

            rs.next();
            service = new Service(rs.getInt("serviceId"), rs.getString("sName"),
                    rs.getInt("sView"),rs.getInt("sType"),rs.getInt("period"),rs.getInt("periodType"));
            service.setObjName(rs.getString("o_name"));
            service.setObjectType(rs.getInt("typeId"));
            service.setParentId(rs.getInt("o_parent"));
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return service;
    }
}

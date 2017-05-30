package com.netcraker.project.bd.server.Model.client;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.shared.objects.TSP;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModelTSP {
    private ServletContext context;

    public ModelTSP(ServletContext context) {
        this.context = context;
    }


    public TSP getTSP(int id)
    {

        TSP tsp = null;
        try {
            ModelTariff modelTariff = new ModelTariff(context);
            ModelService modelService = new ModelService(context);

            Connection cn = ListenerContext.getDBOracle(context);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("SELECT * FROM TABLE(GETOBJECTS.TSP("+id+"))");

            rs.next();
            tsp = new TSP(rs.getInt("tspId"),rs.getDouble("price"),null,null);
            tsp.setObjectType(rs.getInt("typeId"));
            tsp.setObjName(rs.getString("o_name"));
            tsp.setParentId(rs.getInt("o_parent"));
            tsp.setService(modelService.getById(rs.getInt("serviceId")));
            tsp.setTariff(modelTariff.getById(rs.getInt("tariffId")));

            st.close();
        }
        catch (Exception e) {e.printStackTrace();}

        return tsp;
    }
}

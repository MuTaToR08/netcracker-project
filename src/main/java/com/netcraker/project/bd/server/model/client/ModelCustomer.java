package com.netcraker.project.bd.server.model.client;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.shared.objects.client.CSI;
import com.netcraker.project.bd.shared.objects.client.Customer;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModelCustomer {

    private ServletContext context;

    public ModelCustomer(ServletContext context) {
        this.context = context;
    }

    public List<CSI> getCSI(int customerId)
    {
        ModelTSP modelTSP = new ModelTSP(context);
        List<CSI> list= new ArrayList<>();

        try {
            Connection cn = ListenerContext.getDBOracle(context);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("SELECT csiId,statusId,(dateEnd) dateEnd," +
                    "(dateStart) dateStart,tspId,typeId,o_name,o_parent FROM TABLE(GETOBJECTS.CSI("+customerId+"))");

            while(rs.next()) {
                CSI csi = new CSI(rs.getInt("csiId"),rs.getInt("statusID"),
                       rs.getString("dateStart"),rs.getString("dateEnd"),modelTSP.getById(rs.getInt("tspId"))
                        );
                csi.setObjectType(rs.getInt("typeId"));
                csi.setObjName(rs.getString("o_name"));
                csi.setParentId(rs.getInt("o_parent"));
                list.add(csi);
            }
            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Customer getById(int customerId)
    {
        Customer ret = null;
        try {
            Connection cn = ListenerContext.getDBOracle(context);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("SELECT csiId,statusId,(dateEnd) dateEnd," +
                    "(dateStart) dateStart,tspId,typeId,o_name,o_parent FROM TABLE(GETOBJECTS.CUSTOMER("+customerId+"))");
            rs.next();
            // CSI csi = new CSI(rs.getInt("csiId"),rs.getInt("statusID"),
            //    rs.getString("dateStart"),rs.getString("dateEnd"),modelTSP.getTSP(rs.getInt("tspId"))
            // );
            // csi.setObjectType(rs.getInt("typeId"));
            // csi.setObjName(rs.getString("o_name"));
            // csi.setParentId(rs.getInt("o_parent"));


            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

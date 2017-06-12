package com.netcraker.project.bd.server.model.client;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.shared.objects.client.Billing;
import com.netcraker.project.bd.shared.objects.client.CSI;
import com.netcraker.project.bd.shared.objects.client.Customer;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelCustomer {

    private ServletContext context;

    public ModelCustomer(ServletContext context) {
        this.context = context;
    }

    public List<Billing> getBillings(int customerId)
    {
        return new ModelBilling(context).getOfCustomer(customerId);
    }
    public List<Billing> getBillingsSuccess(int customerId)
    {

        return new ModelBilling(context).getOfCustomer(customerId).stream()
            .filter(billing -> billing.getStatus()==31)
            .collect(Collectors.toList());
    }
    public List<CSI> getCSI(int customerId)
    {
        ModelTSP modelTSP = new ModelTSP(context);
        List<CSI> list= new ArrayList<>();

        try {
            Connection cn = ListenerContext.getDBOracle(context);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("SELECT csiId,statusId,(dateEnd) dateEnd," +
                    "(dateStart) dateStart,tspId,typeId,o_name,o_parent FROM TABLE(GETOBJECTS.CSICustomer("+customerId+"))");

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


            ResultSet rs = st.executeQuery ("SELECT * FROM TABLE(CUSTUMER.GetCustomer("+customerId+"))");
            rs.next();
            ret  = new Customer(rs.getInt("customerId"),rs.getInt("typeId"),rs.getString("fio"),rs.getInt("inn"),
                    rs.getInt("phone"),rs.getString("login"),rs.getString("password"),
                    new ModelTariff(context).getById( rs.getInt("tariffId")),rs.getInt("status"),rs.getDouble("balance"));
            // CSI csi = new CSI(rs.getInt("csiId"),rs.getInt("statusID"),
            //    rs.getString("dateStart"),rs.getString("dateEnd"),modelTSP.getTSP(rs.getInt("tspId"))
            // );
            // csi.setObjectType(rs.getInt("typeId"));
            // csi.setObjName(rs.getString("o_name"));
            ret.setParentId(rs.getInt("o_parent"));


            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }
}

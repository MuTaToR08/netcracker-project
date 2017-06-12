package com.netcraker.project.bd.server.model.client;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.client.Billing;

import javax.servlet.ServletContext;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ModelBilling implements DefaultMethod<Billing> {

    private ServletContext context;

    public ModelBilling(ServletContext context) {
        this.context = context;
    }

    @Override
    public Billing getById(int id)  {
        try {
            return (Billing) new AbstractAccess(context,this).templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    List<Billing> getOfCustomer(int id)
    {
        List<Billing> ret = new ArrayList<>();
        Connection cn = ListenerContext.getDBOracle(context);
        CallableStatement st;

        try {
            st = cn.prepareCall(
                    "SELECT gb.* FROM TABLE(getobjects.billing) gb,(SELECT * FROM objects WHERE object_type_id = 34" +
                    "START WITH object_id = "+id+" " +
                    "connect by PRIOR object_id = container_id) b" +
                    " WHERE b.object_id = gb.billingid");

            ResultSet rs = st.executeQuery();
            while(rs.next())
                ret.add(createObject(rs));

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public List<Billing> getAll(){
        try {
            return (List<Billing>) new AbstractAccess(context,this).templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public String getObjectName() {
        return "billing";
    }

    @Override
    public Billing createObject(ResultSet rs) throws SQLException {
        return new Billing(rs.getInt("billingID"),rs.getString("startDate"),
                rs.getString("endDate"),rs.getInt("status"),rs.getInt("amount"));
    }
}

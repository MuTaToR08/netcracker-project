package com.netcraker.project.bd.server.model.networked;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.networked.Carrier;

import javax.servlet.ServletContext;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ModelCarrier implements DefaultMethod<Carrier> {

    private ServletContext context;

    public ModelCarrier(ServletContext context) {
        this.context = context;
    }

    @Override
    public Carrier getByStatment(String SQL) {
        try {
            return (Carrier) new AbstractAccess(context,this).templateGetBySQL(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Carrier getById(int id)  {
        try {
            return (Carrier) new AbstractAccess(context,this).templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getObjectName() {
        return "Carrier";
    }

    @Override
    public List<Carrier> getAll()  {
        try {
            return (List<Carrier>) new AbstractAccess(context,this).templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Carrier createObject(ResultSet rs) throws SQLException {
        int id = rs.getInt("carrierID");
        return new Carrier(id,
                rs.getInt("typeCarrier"),
                rs.getInt("lengthCarrier"),
                rs.getInt("connectors"),
                rs.getInt("capacity"),getPorts(id),getLocation(id));
    }

    public int[] getLocation(int id) throws SQLException {

        Connection cn = ListenerContext.getDBOracle(context);
        CallableStatement st = cn.prepareCall("SELECT object_id FROM REFERENCES where ATTRIBUTE_ID = 71 AND  reference_id = "+id);
        ResultSet rs = st.executeQuery();

        List<Integer> integerList = new ArrayList<Integer>();
        while(rs.next())
            integerList.add(rs.getInt("object_id"));
        st.close();

        int[] ret = new int[integerList.size()];
        for (int i = 0; i < integerList.size(); i++) {
            ret[i] = integerList.get(i);
        }
        return ret;
    }

    public int[] getPorts(int id) throws SQLException {

        Connection cn = ListenerContext.getDBOracle(context);
        CallableStatement st = cn.prepareCall("SELECT object_id FROM REFERENCES where ATTRIBUTE_ID = 70 AND  reference_id = "+id);
        ResultSet rs = st.executeQuery();

        List<Integer> integerList = new ArrayList<Integer>();
        while(rs.next())
            integerList.add(rs.getInt("object_id"));
        st.close();

        int[] ret = new int[integerList.size()];
        for (int i = 0; i < integerList.size(); i++) {
            ret[i] = integerList.get(i);
        }
        return ret;
    }
}

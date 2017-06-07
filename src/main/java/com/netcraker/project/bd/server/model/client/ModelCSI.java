package com.netcraker.project.bd.server.model.client;

import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.client.CSI;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class ModelCSI implements DefaultMethod<CSI> {
    private ServletContext context;

    public ModelCSI(ServletContext context) {
        this.context = context;
    }

    @Override
    public CSI getById(int id)  {
        try {
            return (CSI) new AbstractAccess(context,this).templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public List<CSI> getAll() {
        try {
            return (List<CSI>) new AbstractAccess(context,this).templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public CSI createObject(ResultSet rs) throws SQLException {
        return new CSI(rs.getInt("csiID"),rs.getInt("statusid"),rs.getString("dateStart"),rs.getString("dateEnd"),new ModelTSP(context).getById(rs.getInt("tspId")));
    }

    @Override
    public String getObjectName() {
        return "CSI";
    }
}

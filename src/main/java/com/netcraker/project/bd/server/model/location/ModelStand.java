package com.netcraker.project.bd.server.model.location;

import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.location.Stand;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class ModelStand implements DefaultMethod<Stand> {
    private ServletContext context;

    public ModelStand(ServletContext context) {
        this.context = context;
    }

    @Override
    public Stand getById(int id)  {
        try {
            return (Stand) new AbstractAccess(context,this).templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Stand> getAll() {
        try {
            return (List<Stand>) new AbstractAccess(context,this).templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Stand createObject(ResultSet rs) throws SQLException {
        return new Stand(rs.getInt("standID"),rs.getString("standSize"),rs.getInt("slots"));
    }

    @Override
    public String getObjectName() {
        return "stand";
    }
}

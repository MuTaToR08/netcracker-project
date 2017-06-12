package com.netcraker.project.bd.server.model.networked;

import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.networked.Router;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ModelRouter implements DefaultMethod<Router>{

    private ServletContext context;

    public ModelRouter(ServletContext context) {
        this.context = context;
    }

    @Override
    public Router getById(int id) {
        try {
            return (Router) new AbstractAccess(context,this).templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Router> getAll() {
        try {
            return (List<Router>) new AbstractAccess(context,this).templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Router createObject(ResultSet rs) throws SQLException {
        return new Router(rs.getInt("routerID"),rs.getString("mac"),rs.getString("ip"),rs.getString("mask"),rs.getInt("roleRouter"));
    }

    @Override
    public String getObjectName() {
        return "router";
    }
}

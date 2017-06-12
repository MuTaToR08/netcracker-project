package com.netcraker.project.bd.server.model.networked;

import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.networked.Switch;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ModelSwitch implements DefaultMethod<Switch>{

    private ServletContext context;

    public ModelSwitch(ServletContext context) {
        this.context = context;
    }

    @Override
    public Switch getById(int id) {
        try {
            return (Switch) new AbstractAccess(context,this).templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public List<Switch> getAll() {
        try {
            return (List<Switch>) new AbstractAccess(context,this).templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Switch createObject(ResultSet rs) throws SQLException {
        return new Switch(rs.getInt("switchID"),rs.getInt("roleSwitch"),rs.getString("mac"));
    }

    @Override
    public String getObjectName() {
        return "DEVSWITCH";
    }
}

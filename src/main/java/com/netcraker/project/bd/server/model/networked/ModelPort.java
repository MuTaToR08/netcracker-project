package com.netcraker.project.bd.server.model.networked;

import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.networked.Carrier;
import com.netcraker.project.bd.shared.objects.networked.Port;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ModelPort implements DefaultMethod<Port>{

    private ServletContext context;

    public ModelPort(ServletContext context) {
        this.context = context;
    }

    @Override
    public Port getById(int id) {
        try {
            return (Port) new AbstractAccess(context,this).templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Port> getAll(){
        try {
            return (List<Port>) new AbstractAccess(context,this).templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Port createObject(ResultSet rs) throws SQLException {
        return new Port(rs.getInt("portID"),rs.getInt("num"),rs.getBoolean("enabled"),rs.getInt("typePort"));
    }

    @Override
    public String getObjectName() {
        return "port";
    }

    public Carrier getCarrier(int id) {
       return new ModelCarrier(context).getByStatment("SELECT REFERENCE_ID FROM references " +
               "WHERE object_id = "+id+" AND attribute_id = 70");
    }
}

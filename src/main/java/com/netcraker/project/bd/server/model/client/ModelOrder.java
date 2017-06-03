package com.netcraker.project.bd.server.model.client;

import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.client.Order;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ModelOrder implements DefaultMethod<Order> {
    private ServletContext context;

    public ModelOrder(ServletContext context) {
        this.context = context;
    }

    @Override
    public Order getById(int id) {
        try {
            return (Order)new AbstractAccess(context,this).templateGetById("orders",id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> getAll() {
        try {
            return (List<Order>) new AbstractAccess(context,this).templateGetAll("orders");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Order createObject(ResultSet rs) throws SQLException {
        return new Order(rs.getInt("orderId"),rs.getString("endDate"),rs.getString("startDate"),rs.getInt("status"),rs.getInt("typeOrder"),rs.getInt("csi"));
    }

}

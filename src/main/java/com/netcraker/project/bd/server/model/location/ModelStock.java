package com.netcraker.project.bd.server.model.location;

import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.location.Stock;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class ModelStock implements DefaultMethod<Stock> {

    private ServletContext context;

    public ModelStock(ServletContext context) {
        this.context = context;
    }

    @Override
    public Stock getById(int id) {
        try {
            return (Stock) new AbstractAccess(context,this).templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getObjectName() {
        return "stock";
    }

    @Override
    public List<Stock> getAll() {
        try {
            return  (List<Stock>) new AbstractAccess(context,this).templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Stock createObject(ResultSet rs) throws SQLException {
        return new Stock(rs.getInt("stockId"),rs.getInt("num"),rs.getDouble("stockSize"));
    }
}

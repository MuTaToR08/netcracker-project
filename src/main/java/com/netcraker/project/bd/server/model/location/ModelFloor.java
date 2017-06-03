package com.netcraker.project.bd.server.model.location;

import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.location.Floor;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ModelFloor implements DefaultMethod<Floor> {

    private ServletContext context;

    public ModelFloor(ServletContext context) {
        this.context = context;
    }

    @Override
    public String getObjectName() {
        return "floor";
    }

    @Override
    public Floor getById(int id) {
        AbstractAccess loader = new AbstractAccess(context,this)/* {
            @Override
            protected ObjectBD resultSet(ResultSet rs) throws SQLException {
                ObjectBD ret = createObject(rs);
                defaultObject(ret,rs);
                return ret;
            }
        }*/;
        try {
            return (Floor) loader.templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Floor> getAll() {
        try {
            return (List<Floor>) new AbstractAccess(context,this).templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Floor createObject(ResultSet rs) throws SQLException {
        return new Floor(rs.getInt("floorID"),rs.getInt("num"),rs.getBoolean("ground"),
                rs.getInt("countRoom"),rs.getBoolean("services"));
    }
}

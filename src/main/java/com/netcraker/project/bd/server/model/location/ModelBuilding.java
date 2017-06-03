package com.netcraker.project.bd.server.model.location;

import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.location.Building;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ModelBuilding implements DefaultMethod<Building> {

    private ServletContext context;

    @Override
    public String getObjectName() {
        return "building";
    }

    public ModelBuilding(ServletContext context) {
        this.context = context;
    }

    public Building createObject(ResultSet rs) throws SQLException {
        return new Building(rs.getInt("buildingId"),rs.getDouble("lat"),rs.getDouble("lon"),
                rs.getInt("countFloor"),rs.getInt("countFloorGround"),rs.getString("address"));
    }

    public List<Building> getAll(){
        AbstractAccess loader = new AbstractAccess(context,this)/* {
            @Override
            protected ObjectBD resultSet(ResultSet rs) throws SQLException {
                Building ret = createObject(rs);
                defaultObject(ret,rs);
                return ret;
            }
        }*/;
        try {
            return (List<Building>) loader.templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Building getById(int id) {
        AbstractAccess loader = new AbstractAccess(context,this)/* {
            @Override
            protected ObjectBD resultSet(ResultSet rs) throws SQLException {
                Building ret = createObject(rs);
                defaultObject(ret,rs);
                return ret;
            }
        }*/;

        try {
            return (Building) loader.templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}

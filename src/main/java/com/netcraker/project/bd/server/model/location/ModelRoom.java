package com.netcraker.project.bd.server.model.location;

import com.netcraker.project.bd.server.model.AbstractAccess;
import com.netcraker.project.bd.server.model.DefaultMethod;
import com.netcraker.project.bd.shared.objects.location.Room;

import javax.servlet.ServletContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ModelRoom implements DefaultMethod<Room> {

    private ServletContext context;

    @Override
    public String getObjectName() {
        return "room";
    }

    public ModelRoom(ServletContext context) {
        this.context = context;
    }

    @Override
    public Room getById(int id)  {
        try {
            return (Room) new AbstractAccess(context,this).templateGetById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> getAll() {
        try {
            return (List<Room>) new AbstractAccess(context,this).templateGetAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    @Override
    public Room createObject(ResultSet rs) throws SQLException {
        return new Room(rs.getInt("roomId"),rs.getDouble("roomSize"),rs.getBoolean("services"),rs.getBoolean("closed"),rs.getInt("num"));
    }
}

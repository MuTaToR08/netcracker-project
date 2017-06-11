package com.netcraker.project.bd.server.model;

import com.netcraker.project.bd.shared.objects.ObjectBD;
import com.netcraker.project.bd.shared.objects.networked.Port;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DefaultMethod<T extends ObjectBD> {
    T getById(int id);
    List<T> getAll();
    default T getByStatment(String SQL){return null;};

    abstract ObjectBD createObject(ResultSet rs) throws SQLException;
    default String getObjectName(){return "";}
}

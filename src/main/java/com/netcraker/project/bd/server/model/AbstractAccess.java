package com.netcraker.project.bd.server.model;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.shared.objects.ObjectBD;

import javax.servlet.ServletContext;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbstractAccess {

    private ServletContext context;
    private DefaultMethod<? extends ObjectBD> dm;

    public AbstractAccess(ServletContext context, DefaultMethod defaultMethod) {
        this.context = context;
        dm = defaultMethod;
    }

    private ObjectBD resultSet(ResultSet rs) throws SQLException
    {
        ObjectBD ret = dm.createObject(rs);
        defaultObject(ret,rs);
        return ret;
    }

    public ObjectBD templateGetById( Integer id) throws SQLException {
        if(Objects.equals(dm.getObjectName(), ""))
            throw new SQLException("not name return function");
        return templateGetById(dm.getObjectName(),id);
    }

    public ObjectBD templateGetBySQL( String sql) throws SQLException {
        if(Objects.equals(dm.getObjectName(), ""))
            throw new SQLException("not name return function");
        return templateGetById(dm.getObjectName(),sql);
    }

    public ObjectBD templateGetById(String table, Integer id) throws SQLException {
        return templateGetById(table,"("+id+")");
    }

    public ObjectBD templateGetById(String table, String SQL) throws SQLException {
        String statementPatern = "(("+SQL+"))";

        Connection cn = ListenerContext.getDBOracle(context);
        CallableStatement st = cn.prepareCall("SELECT * FROM TABLE(GETOBJECTS."+table+statementPatern+")");

        ResultSet rs = st.executeQuery();
        ObjectBD ret = null;
        if(rs.next())
            ret = resultSet(rs);

        st.close();
        return ret;
    }

    private static void defaultObject(ObjectBD ret, ResultSet rs){
        try {
            if(rs.isClosed() || ret == null)
                    return;
        } catch (SQLException e) {}

        try {
            ret.setObjectType(rs.getInt("typeID"));
        } catch (SQLException e) {}

        try {
            ret.setParentId(rs.getInt("o_parent"));
        } catch (SQLException e) { }

        try {
            ret.setObjName(rs.getString("o_name"));
        } catch (SQLException e) {}

        try {
            ret.setObjDesc(rs.getString("o_desc"));
        } catch (SQLException e) {}
    }

    public List<? extends ObjectBD> templateGetAll(String table) throws SQLException {

        List<ObjectBD> ret = new ArrayList<>();
        Connection cn = ListenerContext.getDBOracle(context);
        CallableStatement st = cn.prepareCall("SELECT * FROM TABLE(GETOBJECTS."+table+")");
        ResultSet rs = st.executeQuery();
        while(rs.next())
            ret.add(resultSet(rs));
        st.close();
        return ret;
    }

    public List<? extends ObjectBD> templateGetAll() throws SQLException {
        if(Objects.equals(dm.getObjectName(), ""))
            throw new SQLException("not name return function");
        return templateGetAll(dm.getObjectName());
    }

}

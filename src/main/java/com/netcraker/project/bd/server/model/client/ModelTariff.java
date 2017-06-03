package com.netcraker.project.bd.server.model.client;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.shared.objects.client.Tariff;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ModelTariff {

    private ServletContext context;

    public ModelTariff(ServletContext context) {
        this.context = context;
    }

    public Tariff getById(int id)
    {
        Tariff tariff = null;
        try {
            Connection cn = ListenerContext.getDBOracle(context);

            Statement st = cn.createStatement();

            ResultSet rs = st.executeQuery ("SELECT * FROM TABLE(getobjects.tariff("+id+"))");

            while(rs.next()) {
                tariff = new Tariff(rs.getInt("tariffId"), rs.getString("tariffName"), rs.getInt("statusid"));
            }

            st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return tariff;
    }
}

package com.netcraker.project.bd.server.services.api.client;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.server.model.client.ModelCustomer;
import com.netcraker.project.bd.server.model.client.ModelTariff;
import com.netcraker.project.bd.shared.objects.client.Billing;
import com.netcraker.project.bd.shared.objects.client.CSI;
import com.netcraker.project.bd.shared.objects.client.Customer;
import com.sun.deploy.util.StringUtils;
import org.apache.commons.collections.map.HashedMap;
import org.fusesource.restygwt.client.RestService;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Path("customer")
public class ServiceCustomer implements RestService {

    @Context
    private ServletContext context;

    @GET
    @Produces("application/json")
    @Path("{id}")
    public Customer get(@PathParam("id") int id)
    {
        Customer customer = null;
        try {
            Connection cn = ListenerContext.getDBOracle(context);

            ModelTariff modelTariff = new ModelTariff(context);

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("SELECT * FROM TABLE(CUSTUMER.GetCustomer("+id+"))");
            rs.next();
            customer = new Customer(rs.getInt("customerId"),rs.getInt("typeId"),rs.getString("fio"),rs.getInt("inn"),
                    rs.getInt("phone"),rs.getString("login"),rs.getString("password"),
                    modelTariff.getById( rs.getInt("tariffId")),rs.getInt("status"),rs.getDouble("balance"));
            st.close();
        }
        catch (Exception e) {e.printStackTrace();}
        return customer;
    }

    @GET
    @Produces("application/json")
    @Path("{id}/csi")
    public List<CSI> getCSI(@PathParam("id") int id)
    {
        ModelCustomer modelCustomer = new ModelCustomer(context);

        return modelCustomer.getCSI(id);
    }

    @GET
    @Produces("application/json")
    @Path("{id}/billing")
    public List<Billing> getBilling(@PathParam("id") int id)
    {
        ModelCustomer modelCustomer = new ModelCustomer(context);

        return modelCustomer.getBillings(id);
    }

    @GET
    @Produces("application/json")
    @Path("{id}/billing/success")
    public List<Billing> getBillingSuccess(@PathParam("id") int id)
    {
        ModelCustomer modelCustomer = new ModelCustomer(context);

        return modelCustomer.getBillingsSuccess(id);
    }

    @GET
    @Produces("application/json")
    public Map<Integer,Customer> getAll()
    {
        Map<Integer,Customer> customers = new HashedMap();
        try {
            Connection cn = ListenerContext.getDBOracle(context);
            ModelTariff modelTariff = new ModelTariff(context);
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery ("SELECT * FROM TABLE(CUSTUMER.GetCustomer())");
            List<String> listId = new ArrayList<>();
            while(rs.next()) {
                listId.add(rs.getString("customerID"));
                customers.put(rs.getInt("customerId"), new Customer(rs.getInt("customerId"), rs.getInt("typeId"), rs.getString("fio"), rs.getInt("inn"),
                        rs.getInt("phone"), rs.getString("login"), rs.getString("password"),
                        modelTariff.getById( rs.getInt("tariffId")), rs.getInt("status"), rs.getDouble("balance")));
            }
            rs = st.executeQuery ("SELECT * FROM objects WHERE object_id in ("+ StringUtils.join(listId,",")+")");
            while(rs.next()) {
                int id = rs.getInt("object_id");
                Customer cs = customers.get(id);
                cs.setObjName(rs.getString("name"));
                cs.setParentId(rs.getInt("container_id"));
                cs.setObjectType(rs.getInt("object_type_id"));

            }
                st.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

}

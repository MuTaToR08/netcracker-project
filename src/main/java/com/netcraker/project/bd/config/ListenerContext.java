package com.netcraker.project.bd.config;

import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ListenerContext implements ServletContextListener {

    public static Connection getDBOracle(ServletContext context)
    {
        return (Connection)context.getAttribute("dateBase");
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try {
            DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
        }
        catch (Exception e) {
            e.printStackTrace();

        }

        try {
            Connection cn =
                    DriverManager.getConnection ("jdbc:oracle:thin:@localhost:1521:PROJECT","proj","qwe123");
            ServletContext servletContext = servletContextEvent.getServletContext();

            servletContext.setAttribute("dateBase",cn);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        try {
            ((Connection)servletContextEvent.getServletContext().getAttribute("dateBase")).close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.netcraker.project.bd.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcraker.project.bd.server.services.api.ServiceStaticObject;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ListenerBodyContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServiceStaticObject s = new ServiceStaticObject();
        s.setContext(servletContextEvent.getServletContext());
        ObjectMapper mapper = new ObjectMapper();

        try {
            servletContextEvent.getServletContext().setAttribute("objectType",mapper.writeValueAsString(s.getAllObjects()));
            servletContextEvent.getServletContext().setAttribute("statusList",mapper.writeValueAsString(s.getAllStatus()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}


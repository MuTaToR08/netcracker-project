package com.netcraker.project.bd.shared.objects.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

@Json
public class Room extends ObjectBD {
    private double size;
    private boolean services;
    private boolean closed;

    @JsonCreator
    public Room(@JsonProperty("id") int id,@JsonProperty("size") double size,@JsonProperty("services") boolean services,
                @JsonProperty("closed") boolean closed) {
        super(id);
        this.size = size;
        this.services = services;
        this.closed = closed;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                "size=" + size +
                ", services=" + services +
                ", closed=" + closed +
                '}';
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isServices() {
        return services;
    }

    public void setServices(boolean services) {
        this.services = services;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}

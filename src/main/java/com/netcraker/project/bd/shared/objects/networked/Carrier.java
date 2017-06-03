package com.netcraker.project.bd.shared.objects.networked;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

@Json
public class Carrier extends ObjectBD {
    private int type;
    private int length;
    private int connectors;
    private int capacity;
    private int ports[];
    private int locations[];


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int[] getPorts() {
        return ports;
    }

    public void setPorts(int[] ports) {
        this.ports = ports;
    }
    public void addPort(int port)
    {
        for (int i : ports) {
            if(port == i)
                return;
        }
        int[] tmp = ports;
        int[] ports = new int[tmp.length+1];
        for (int i = 0; i < tmp.length; i++) {
            ports[i] = tmp[i];
        }
        ports[tmp.length] = port;
    }



    public int getLength() {

        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getConnectors() {
        return connectors;
    }

    public void setConnectors(int connectors) {
        this.connectors = connectors;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addHardware(int hardware)
    {
        for (int i : locations)
            if (hardware == i)
                return;

        int[] tmp = locations;
        int[] hardwares = new int[tmp.length+1];
        for (int i = 0; i < tmp.length; i++) {
            hardwares[i] = tmp[i];
        }
        hardwares[tmp.length] = hardware;
    }

    public int[] getLocations() {
        return locations;
    }

    public void setLocations(int[] locations) {
        this.locations = locations;
    }

    @JsonCreator
    public Carrier(@JsonProperty("id") int id, @JsonProperty("type") int type,
                   @JsonProperty("length") int length,@JsonProperty("connectors") int connectors,
                   @JsonProperty("capacity") int capacity, @JsonProperty("ports") int[] ports,
                   @JsonProperty("locations") int[] locations) {
        super(id);
        this.type = type;
        this.length = length;
        this.connectors = connectors;
        this.capacity = capacity;
        this.ports = ports;
        this.locations = locations;
    }


}

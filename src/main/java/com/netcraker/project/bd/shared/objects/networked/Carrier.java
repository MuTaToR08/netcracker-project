package com.netcraker.project.bd.shared.objects.networked;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.google.gwt.user.client.Window;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Json
public class Carrier extends ObjectBD {
    private int type;
    private int length;
    private int connectors;
    private int capacity;
    private int ports[];
    private int locations[];

    public Carrier(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("type") && e.get("type") != null)
            type = Integer.valueOf(e.get("type").toString());
        if(e.containsKey("length") && e.get("length") != null)
            length = Integer.valueOf(e.get("length").toString());
        if(e.containsKey("connectors") && e.get("connectors") != null)
            connectors = Integer.valueOf(e.get("connectors").toString());
        if(e.containsKey("capacity") && e.get("capacity") != null)
            capacity = Integer.valueOf(e.get("capacity").toString());

        if(e.containsKey("ports") && e.get("ports") != null)/*ports = new int[]{*/ {
            ArrayList p = (ArrayList) e.get("ports");
            ports = new int[p.size()];
            for (int i = 0; i < p.size(); i++) {
                ports[i] = Integer.valueOf(p.get(i).toString());
            }
        }
        if(e.containsKey("locations") && e.get("locations") != null)/*ports = new int[]{*/ {
            ArrayList p = (ArrayList) e.get("locations");
            locations = new int[p.size()];
            for (int i = 0; i < p.size(); i++) {
                locations[i] = Integer.valueOf(p.get(i).toString());
            }
        }
        //if(e.containsKey("locations") && e.get("locations") != null)locations = (e.get("locations").toString());
        //if(e.containsKey("") && e.get("") != null) = (e.get("").toString());

    }


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

    @Override
    public String toString() {
        return "Carrier{" +
                "type=" + type +
                ", length=" + length +
                ", connectors=" + connectors +
                ", capacity=" + capacity +
                ", ports=" + Arrays.toString(ports) +
                ", locations=" + Arrays.toString(locations) +
                '}';
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

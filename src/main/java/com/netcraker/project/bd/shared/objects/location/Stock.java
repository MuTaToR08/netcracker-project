package com.netcraker.project.bd.shared.objects.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Stock extends ObjectBD {
    private int number;
    private double size;

    @JsonCreator
    public Stock(@JsonProperty("id") int id,@JsonProperty("number") int number,@JsonProperty("size") double size) {
        super(id);
        this.number = number;
        this.size = size;
    }

    public Stock(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("number") && e.get("number") != null)
            number = Integer.valueOf(e.get("number").toString());
        if(e.containsKey("size") && e.get("size") != null)
            size = (Double) e.get("size");

    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "number=" + number +
                ", size=" + size +
                '}';
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}

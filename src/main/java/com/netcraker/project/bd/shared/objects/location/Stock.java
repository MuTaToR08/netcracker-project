package com.netcraker.project.bd.shared.objects.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

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

    public int getNumber() {

        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                "number=" + number +
                ", size=" + size +
                '}';
    }

    public void setSize(double size) {
        this.size = size;
    }
}

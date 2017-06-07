package com.netcraker.project.bd.shared.objects.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Stock extends ObjectBD {
    private int number;
    private String size;

    @JsonCreator
    public Stock(@JsonProperty("id") int id,@JsonProperty("number") int number,@JsonProperty("size") String size) {
        super(id);
        this.number = number;
        this.size = size;
    }

    public Stock(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("number") && e.get("number") != null)
            number = Integer.valueOf(e.get("number").toString());
        else
            number = 0;
        if(e.containsKey("size") && e.get("size") != null)
            size =  e.get("size").toString();
        else
            size = "0x0";

    }

    @Override
    public String getHtmlUl() {
        return publicName()+" №"+number+" площадь:"+size;
    }

    @Override
    public String getObjectClass() {
        return "stock";
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

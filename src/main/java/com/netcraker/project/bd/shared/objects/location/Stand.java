package com.netcraker.project.bd.shared.objects.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Stand  extends ObjectBD{
    private String size;
    private int slots;

    @JsonCreator
    public Stand(@JsonProperty("id") int id,@JsonProperty("size") String size,@JsonProperty("slots") int slots) {
        super(id);
        this.size = size;
        this.slots = slots;
    }

    @Override
    public String getHtmlUl() {
        return "Сойка. Размер:"+size+", слотов:"+slots;
    }

    /*@Override
    public String publicName() {
        return super.publicName();
    }*/

    @Override
    @JsonIgnore
    public String getObjectClass() {
        return "stand";
    }

    public Stand(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("size") && e.get("size") != null)size = e.get("size").toString();
        else
            size = "0x0";
        if(e.containsKey("slots") && e.get("slots") != null) slots = Integer.valueOf(e.get("slots").toString());
        else
            slots = 0;
    }

    @Override
    public String toString() {
        return "Stand{" +
                "id='" + id +
                "size='" + size + '\'' +
                ", slots=" + slots +
                '}';
    }

    public String getSize() {

        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getSlots() {

        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }
}

package com.netcraker.project.bd.shared.objects.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

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

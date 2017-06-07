package com.netcraker.project.bd.shared.objects.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Floor extends ObjectBD {
    private int num;
    private boolean ground;
    private int countRoom;
    private boolean services;

    @JsonCreator
    public Floor(@JsonProperty("id") int id,@JsonProperty("num") int num,@JsonProperty("ground") boolean ground,
                 @JsonProperty("countROom")int countRoom,@JsonProperty("services") boolean services) {
        super(id);
        this.num = num;
        this.ground = ground;
        this.countRoom = countRoom;
        this.services = services;
    }

    @Override
    @JsonIgnore
    public String getHtmlUl() {
        return publicName()+" "+(ground?"↓":"↑")+" "+(services?"(служебный)":"")+", "+countRoom+" кол-во кабинетов";
    }

    @Override
    @JsonIgnore
    public String publicName() {
        return "Этаж:"+num;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "id=" + id +
                "num=" + num +
                ", ground=" + ground +
                ", countRoom=" + countRoom +
                ", services=" + services +
                '}';
    }

    public Floor(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("num") && e.get("num") != null)num = Integer.valueOf(e.get("num").toString());
        if(e.containsKey("ground") && e.get("ground") != null)ground = Boolean.valueOf(e.get("ground").toString());
        if(e.containsKey("countRoom") && e.get("countRoom") != null)countRoom = Integer.valueOf(e.get("countRoom").toString());
        if(e.containsKey("services") && e.get("services") != null)services = Boolean.valueOf(e.get("services").toString());

    }

    @Override
    @JsonIgnore
    public String getObjectClass() {
        return "floor";
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isGround() {
        return ground;
    }

    public void setGround(boolean ground) {
        this.ground = ground;
    }

    public int getCountRoom() {
        return countRoom;
    }

    public void setCountRoom(int countRoom) {
        this.countRoom = countRoom;
    }

    public boolean isServices() {
        return services;
    }

    public void setServices(boolean services) {
        this.services = services;
    }
}

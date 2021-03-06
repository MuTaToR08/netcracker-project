package com.netcraker.project.bd.shared.objects.networked;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.client.BD;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import com.netcraker.project.bd.shared.objects.controled.ExtendsButton;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Port extends ObjectBD implements ExtendsButton {
    private int num;
    private boolean enable;
    private int type;


    @JsonCreator
    public Port(@JsonProperty("id") int id,@JsonProperty("num") int num,
                @JsonProperty("enable") boolean enable,@JsonProperty("type") int type) {
        super(id);
        this.num = num;
        this.enable = enable;
        this.type = type;
    }

    public Port(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("num") && e.get("num") != null) num = Integer.valueOf(e.get("num").toString());
        if(e.containsKey("type") && e.get("type") != null)type = Integer.valueOf(e.get("type").toString());
        if(e.containsKey("enable") && e.get("enable") != null)enable = Boolean.valueOf(e.get("enable").toString());

    }

    @JsonIgnore
    @Override
    public String getObjectClass() {
        return "port";
    }

    @Override
    @JsonIgnore
    public String getHtmlUl() {
        return "№"+num+"("+ (BD.statuses.get(type)==null?"":BD.statuses.get(type).getText())+")"+(enable?"<div class='icon port-enable'></div>":"<div class='icon port-disable'></div>");
    }


    @Override
    public String toString() {
        return "Port{" +
                "num=" + num +
                ", enable=" + enable +
                ", type=" + type +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}

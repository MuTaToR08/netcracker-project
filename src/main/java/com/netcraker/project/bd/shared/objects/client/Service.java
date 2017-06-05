package com.netcraker.project.bd.shared.objects.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Service extends ObjectBD {

    //private String name;
    private int view;
    private int type;
    private int period;
    private int periodType;

    public Service(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("view") && e.get("view") != null) view = Integer.valueOf(e.get("view").toString());
        if(e.containsKey("type") && e.get("type") != null) type = Integer.valueOf(e.get("type").toString());
        if(e.containsKey("period") && e.get("period") != null) period = Integer.valueOf(e.get("period").toString());
        if(e.containsKey("periodType") && e.get("periodType") != null) periodType =Integer.valueOf(e.get("periodType").toString());
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + getObjName() + '\'' +
                ", view=" + view +
                ", type=" + type +
                ", period=" + period +
                ", periodType=" + periodType +
                '}';
    }

    public String getName() {
        //return name;
        return getObjName();
    }

    public void setName(String name) {
        //this.name = name;
        setObjName(name);
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriodType() {
        return periodType;
    }

    public void setPeriodType(int periodType) {
        this.periodType = periodType;
    }

    @JsonCreator
    public Service(@JsonProperty("id") int id,@JsonProperty("name") String name,
                   @JsonProperty("view") int view,@JsonProperty("type") int type,
                   @JsonProperty("period") int period,@JsonProperty("periodType") int periodType) {
        super(id);
        //this.name = name;
        setObjName(name);
        this.view = view;
        this.type = type;
        this.period = period;
        this.periodType = periodType;
    }


    public Service(int id) {
        super(id);
    }
}

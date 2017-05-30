package com.netcraker.project.bd.shared.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fusesource.restygwt.client.Json;

@Json
public class Service extends ObjectBD {
    private int id;
    private String name;
    private int view;
    private int type;
    private int period;
    private int periodType;

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", view=" + view +
                ", type=" + type +
                ", period=" + period +
                ", periodType=" + periodType +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.id = id;
        this.name = name;
        this.view = view;
        this.type = type;
        this.period = period;
        this.periodType = periodType;
    }

    public int getId() {
        return id;
    }


    public Service(int id) {

        this.id = id;
    }

    public Service() {

    }
}

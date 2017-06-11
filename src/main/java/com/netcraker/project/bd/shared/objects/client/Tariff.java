package com.netcraker.project.bd.shared.objects.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Tariff extends ObjectBD {
    //private String tariffName;
    private int statusId;

    public Tariff(int tariffId) {
        super(tariffId);
    }

    public Tariff(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("statusId") && e.get("statusId") != null)statusId = Integer.valueOf(e.get("statusId").toString());
    }

    @Override
    public String getObjectClass() {
        return "tariff";
    }

    @Override
    public String toString()
    {
//        return tariffName;
        return getObjName();
//
 }

@JsonIgnore
    public String getTariffName() {
        return getObjName();
    }

    public void setTariffName(String tariffName) {
        setObjName(tariffName);
        //this.tariffName = tariffName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    @JsonCreator
    public Tariff(@JsonProperty("id") int id,@JsonProperty("objName") String tariffName,
                  @JsonProperty("statusId") int statusId) {
        super(id);
        setObjName(tariffName);
        //this.tariffName = tariffName;
        this.statusId = statusId;
    }
}

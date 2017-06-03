package com.netcraker.project.bd.shared.objects.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

@Json
public class Tariff extends ObjectBD {
    //private String tariffName;
    private int statusId;

    public Tariff(int tariffId) {
        super(tariffId);
    }

    @Override
    public String toString()
    {
//        return tariffName;
        return getObjName();
//
 }


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
    public Tariff(@JsonProperty("id") int id,@JsonProperty("tariffName") String tariffName,
                  @JsonProperty("statusId") int statusId) {
        super(id);
        setObjName(tariffName);
        //this.tariffName = tariffName;
        this.statusId = statusId;
    }
}

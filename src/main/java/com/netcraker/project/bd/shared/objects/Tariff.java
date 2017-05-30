package com.netcraker.project.bd.shared.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fusesource.restygwt.client.Json;

@Json
public class Tariff extends ObjectBD {
    private int id;
    private String tariffName;
    private int statusId;

    public Tariff(int tariffId) {
        id= tariffId;
    }

    @Override
    public String toString()
    {
        return tariffName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Tariff() {
    }

    @JsonCreator
    public Tariff(@JsonProperty("id") int id,@JsonProperty("tariffName") String tariffName,@JsonProperty("statusId") int statusId) {

        this.id = id;
        this.tariffName = tariffName;
        this.statusId = statusId;
    }
}

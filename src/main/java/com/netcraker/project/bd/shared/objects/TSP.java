package com.netcraker.project.bd.shared.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fusesource.restygwt.client.Json;

@Json
public class TSP extends ObjectBD {
    private int id;
    private double price;
    private Service service;

    @Override
    public String toString() {
        return "ModelTSP{" +
                "id=" + id +
                ", price=" + price +
                ", service=" + service +
                ", tariff=" + tariff +
                '}';
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public TSP(int id) {

        this.id = id;
    }

    @JsonCreator
    public TSP(@JsonProperty("id") int id,@JsonProperty("price") double price,
               @JsonProperty("service") Service service,@JsonProperty("tariff") Tariff tariff) {

        this.id = id;
        this.price = price;
        this.service = service;
        this.tariff = tariff;
    }

    public TSP() {

    }

    private Tariff tariff;
}

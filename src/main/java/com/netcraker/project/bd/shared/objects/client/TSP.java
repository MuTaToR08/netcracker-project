package com.netcraker.project.bd.shared.objects.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.apache.tapestry.wml.Do;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;


@Json
public class TSP extends ObjectBD {

    private double price;
    private Service service;
    private Tariff tariff;

    public TSP(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("price") && e.get("price") != null)price = (Double)(e.get("price"));
        if(e.containsKey("service") && e.get("service") != null)service = new Service((HashMap<String, Object>) e.get("service"));
        else service = null;
        if(e.containsKey("tariff") && e.get("tariff") != null)tariff = new Tariff((HashMap<String, Object>) e.get("tariff"));
        else tariff = null;
    }

    @Override
    public String getObjectClass() {
        return "tsp";
    }

    @Override
    public String toString() {
        return "ModelTSP{" +
                "id=" + id +
                ", price=" + price +
                ", service=" + service +
                ", tariff=" + tariff +
                '}';
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
        super(id);
    }

    @JsonCreator
    public TSP(@JsonProperty("id") int id,@JsonProperty("price") double price,
               @JsonProperty("service") Service service,@JsonProperty("tariff") Tariff tariff) {
        super(id);
        this.price = price;
        this.service = service;
        this.tariff = tariff;
    }


}

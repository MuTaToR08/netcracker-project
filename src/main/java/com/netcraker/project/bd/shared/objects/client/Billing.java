package com.netcraker.project.bd.shared.objects.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Billing extends ObjectBD {
    private String startDate;
    private String endDate;
    private int status;
    private double amount;


    @JsonCreator
    public Billing(@JsonProperty("id") int id,@JsonProperty("startDate") String startDate,
                   @JsonProperty("endDate") String endDate,@JsonProperty("status") int status,@JsonProperty("amount") double amount) {

        super(id);
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.amount = amount;
    }

    public Billing(HashMap<String, Object> e) throws Exception {
        super(e);
    }

    @Override
    public String toString() {
        return "Billing{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

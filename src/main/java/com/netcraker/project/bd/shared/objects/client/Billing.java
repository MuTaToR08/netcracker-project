package com.netcraker.project.bd.shared.objects.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.netcraker.project.bd.client.BD;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.Date;
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
        if(e.containsKey("startDate") && e.get("startDate") != null)
            startDate = e.get("startDate").toString();
        else
            startDate = null;
        if(e.containsKey("endDate") && e.get("endDate") != null)
            endDate = e.get("endDate").toString();
        else
            endDate = null;
        if(e.containsKey("status") && e.get("status") != null)
            status =Integer.valueOf(e.get("status").toString());
        else
            status = 0;

        if(e.containsKey("amount") && e.get("amount") != null)
            amount = Double.valueOf(e.get("amount").toString());
        else
            amount = 0;
    }

    @Override
    @JsonIgnore
    public String publicName() {
        return "Billing("+BD.statuses.get(status).getText()+")";
    }

    @Override
    @JsonIgnore
    public String getObjectClass() {
        return "billing";
    }

    @Override
    @JsonIgnore
    public String getHtmlUl() {
        Date en = null;
        String st_en = "";
        if(!startDate.equals(""))
            en = new Date(startDate);
        if(en != null)
            st_en = DateTimeFormat.getShortDateFormat().format(en);
        return "start:"+st_en+"|"+publicName();
    }

    @Override
    @JsonIgnore
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

package com.netcraker.project.bd.shared.objects.client;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.client.BD;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Order extends ObjectBD{
    private String endDate;
    private String startDate;
    private int status;
    private int type;
    private int csi;

    public Order(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("endDate") && e.get("endDate") !=     null) endDate = e.get("endDate").toString();
        else endDate = "";
        if(e.containsKey("startDate") && e.get("startDate") != null) startDate = e.get("startDate").toString();
        else startDate = "";
        if(e.containsKey("status") && e.get("status") !=       null) status =Integer.valueOf(e.get("status").toString());
        else status = 1;
        if(e.containsKey("type") && e.get("type") !=           null) type = Integer.valueOf(e.get("type").toString());
        else type = 1;
        if(e.containsKey("csi") && e.get("csi") !=             null) csi = Integer.valueOf(e.get("csi").toString());
        else csi = 0;
    }

    public int getCsi() {
        return csi;
    }

    public void setCsi(int csi) {
        this.csi = csi;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @JsonCreator
    public Order(@JsonProperty("id") int id,@JsonProperty("endDate") String endDate,@JsonProperty("startDate") String startDate,
                 @JsonProperty("status") int status,@JsonProperty("type") int type,@JsonProperty("csi") int csi) {
        super(id);
        this.endDate = endDate;
        this.startDate = startDate;
        this.status = status;
        this.type = type;
        this.csi = csi;

    }

    @Override
    @JsonIgnore
    public String getObjectClass() {
        return "order";
    }

    @Override
    @JsonIgnore
    public String getHtmlUl() {
        return publicName()+", "+startDate+"-"+endDate+"|"+(BD.statuses.get(type)==null?"":BD.statuses.get(type).getText())+"|<b>"+(BD.statuses.get(status)==null?"":BD.statuses.get(status).getText())+"</b>";
    }

    @Override
    public String toString() {
        return "Order{" +
                "endDate='" + endDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}

package com.netcraker.project.bd.shared.objects.client;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

@Json
public class Order extends ObjectBD{
    private String endDate;
    private String startDate;
    private int status;
    private int type;
    private int csi;

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
    public String toString() {
        return "Order{" +
                "endDate='" + endDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", status=" + status +
                ", type=" + type +
                '}';
    }
}

package com.netcraker.project.bd.shared.objects.location;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

@Json
public class Building extends ObjectBD {
    private double lat;
    private double lon;
    private int countFloor;
    private int countFloorGround;
    private String address;

    public double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                "lat=" + lat +
                ", lon=" + lon +
                ", countFloor=" + countFloor +
                ", countFloorGround=" + countFloorGround +
                ", address='" + address + '\'' +
                '}';
    }

    @JsonCreator
    public Building(@JsonProperty("id") int id,@JsonProperty("lat") double lat,@JsonProperty("lon") double lon,
                    @JsonProperty("countFloor")int countFloor,@JsonProperty("countFloorGround") int countFloorGround,
                    @JsonProperty("address") String address) {
        super(id);
        this.lat = lat;
        this.lon = lon;
        this.countFloor = countFloor;
        this.countFloorGround = countFloorGround;
        this.address = address;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getCountFloor() {
        return countFloor;
    }

    public void setCountFloor(int countFloor) {
        this.countFloor = countFloor;
    }

    public int getCountFloorGround() {
        return countFloorGround;
    }

    public void setCountFloorGround(int countFloorGround) {
        this.countFloorGround = countFloorGround;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getObjectClass(){return "Building";}
}

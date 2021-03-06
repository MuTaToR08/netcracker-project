package com.netcraker.project.bd.shared.objects.networked;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.client.BD;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Router extends NetworkHardware {
    private String ip;
    private String mask;

    @JsonCreator
    public Router(@JsonProperty("id") int id, @JsonProperty("mac") String mac,
                  @JsonProperty("ip") String ip, @JsonProperty("mask") String mask, @JsonProperty("role") int role) {
        super(id, role, mac);
        this.ip = ip;
        this.mask = mask;
    }

    public Router(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("ip") && e.get("ip") != null)ip = (e.get("ip").toString());
        else
            ip = "0.0.0.0";
        if(e.containsKey("mask") && e.get("mask") != null)mask = (e.get("mask").toString());
        else
            mask = "255.255.255.255";

    }

    @Override
    @JsonIgnore
    public String getObjectClass() {
        return "router";
    }

    @Override
    @JsonIgnore
    public String getHtmlUl() {
        return publicName()+" <b>"+(BD.statuses.get(getRole())!=null?BD.statuses.get(getRole()).getText():"")+"</B> "+ip+"/"+mask;
    }


    @Override
    public String toString() {
        return "Router{" +
                "super='" + super.toString() + '\'' +
                ", ip=" + ip +
                ", mask=" + mask +
                '}';
    }

    /*protected String decodeIP()
    {
        return String.valueOf ((byte)(ip >>> 24))+'.'+
                String.valueOf ((byte)(ip >>> 16))+'.'+
                String.valueOf ((byte)(ip >>> 8 ))+'.'+
                String.valueOf ((byte)ip);
    }

    protected String decodeMask()
    {
        return String.valueOf ((byte)(mask >>> 24))+'.'+
                String.valueOf ((byte)(mask >>> 16))+'.'+
                String.valueOf ((byte)(mask >>> 8 ))+'.'+
                String.valueOf ((byte)mask);
    }*/

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }
}

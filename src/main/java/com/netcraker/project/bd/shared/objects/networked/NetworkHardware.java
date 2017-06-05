package com.netcraker.project.bd.shared.objects.networked;

import com.netcraker.project.bd.shared.objects.ObjectBD;

import java.util.HashMap;

abstract class NetworkHardware extends ObjectBD {
    private int role;
    private String mac;

    NetworkHardware(int id, int role, String mac) {
        super(id);
        this.role = role;
        this.mac = mac;
    }

    public NetworkHardware(HashMap<String, Object> e) throws Exception {
        super(e);
        if(e.containsKey("mac") && e.get("mac") != null)mac = (e.get("mac").toString());
        if(e.containsKey("role") && e.get("role") != null)role = Integer.valueOf(e.get("role").toString());

    }

    public int getRole() {

        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}

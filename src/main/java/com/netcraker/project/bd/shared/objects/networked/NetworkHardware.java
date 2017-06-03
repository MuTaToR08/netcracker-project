package com.netcraker.project.bd.shared.objects.networked;

import com.netcraker.project.bd.shared.objects.ObjectBD;

abstract class NetworkHardware extends ObjectBD {
    private int role;
    private String mac;

    NetworkHardware(int id, int role, String mac) {
        super(id);
        this.role = role;
        this.mac = mac;
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

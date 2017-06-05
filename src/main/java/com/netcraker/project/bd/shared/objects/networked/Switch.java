package com.netcraker.project.bd.shared.objects.networked;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fusesource.restygwt.client.Json;

import java.util.HashMap;

@Json
public class Switch extends NetworkHardware {

    @JsonCreator
    public Switch(@JsonProperty("id") int id,@JsonProperty("role") int role,@JsonProperty("mac") String mac) {
        super(id, role, mac);
    }

    public Switch(HashMap<String, Object> e) throws Exception {
        super(e);
    }
}

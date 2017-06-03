package com.netcraker.project.bd.shared.objects.networked;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import org.fusesource.restygwt.client.Json;

@Json
public class Port extends ObjectBD {
    private int num;
    private boolean enable;
    private int type;


    @JsonCreator
    public Port(@JsonProperty("id") int id,@JsonProperty("num") int num,
                @JsonProperty("enable") boolean enable,@JsonProperty("type") int type) {
        super(id);
        this.num = num;
        this.enable = enable;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Port{" +
                "num=" + num +
                ", enable=" + enable +
                ", type=" + type +
                '}';
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

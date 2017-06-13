package com.netcraker.project.bd.shared.containers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.fusesource.restygwt.client.Json;

@Json
public class TreeElement {
    private Object objectBD;
    private int attribute;

    @JsonCreator
    public TreeElement(@JsonProperty("objectBD") Object objectBD,@JsonProperty("attribute") int attribute) {
        this.objectBD = objectBD;
        this.attribute = attribute;
    }

    public Object getObjectBD() {

        return objectBD;
    }

    public void setObjectBD(Object objectBD) {
        this.objectBD = objectBD;
    }

    public int getAttribute() {
        return attribute;
    }

    public void setAttribute(int attribute) {
        this.attribute = attribute;
    }
}

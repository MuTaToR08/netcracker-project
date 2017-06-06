package com.netcraker.project.bd.shared.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gwt.json.client.JSONObject;
import org.fusesource.restygwt.client.Json;

@Json
public class Status {
    private int id;
    private int attrId;
    private String text;

    public Status(JSONObject object) {
        id = Integer.valueOf(object.get("id").isNumber().toString());
        attrId = Integer.valueOf(object.get("attrId").isNumber().toString());
        text =  object.get("text").isString().toString();
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", attrId=" + attrId +
                ", text='" + text + '\'' +
                '}';
    }

    @JsonCreator
    public Status(@JsonProperty("id") int id, @JsonProperty("attrId") int attrId,@JsonProperty("text") String text) {
        this.id = id;
        this.attrId = attrId;
        this.text = text;
    }

    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String publicName() {
        return text;
    }
}

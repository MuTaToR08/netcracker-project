package com.netcraker.project.bd.shared.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gwt.json.client.JSONObject;
import org.fusesource.restygwt.client.Json;

@Json
public class ObjectType {
    private int id;
    private String name;
    private String desc;
    private int parent;

    public ObjectType(JSONObject object) {
        id = Integer.valueOf(object.get("id").isNumber().toString());
        parent = Integer.valueOf(object.get("parent").isNumber().toString());
        name =  object.get("name").isString().toString();
        desc =  object.get("desc").isString().toString();
    }


    public int getId() {
        return id;
    }

    @Override

    public String toString() {
        return "ObjectType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", parent=" + parent +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @JsonCreator
    public ObjectType(@JsonProperty("id") int id,@JsonProperty("name") String name,
                      @JsonProperty("desc") String desc,@JsonProperty("parent") int parent) {
        this.id =(id);
        this.name = name;
        this.desc = desc;
        this.parent = parent;
    }
}

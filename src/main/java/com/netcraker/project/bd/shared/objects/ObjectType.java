package com.netcraker.project.bd.shared.objects;

import org.fusesource.restygwt.client.Json;

@Json
public class ObjectType extends ObjectBD {
    private int id;
    private String name;
    private String desc;
    private int parent;

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

    public ObjectType(int id, String name, String desc, int parent) {

        this.id = id;
        this.name = name;
        this.desc = desc;
        this.parent = parent;
    }

    public ObjectType() {

    }

    @Override
    public String publicName() {
        return name;
    }
}

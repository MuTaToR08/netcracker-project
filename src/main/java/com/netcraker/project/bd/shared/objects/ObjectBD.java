package com.netcraker.project.bd.shared.objects;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class ObjectBD implements Named{

    protected int id;
    private String objName;
    private String objDesc;
    private Integer parentId;
    private int objectType;

    @JsonCreator
    public ObjectBD(@JsonProperty("id") int id) {
        this.id = id;
        parentId = 0;
        objectType = 0;
        objDesc = "";
        objName = "";
    }
    //public ObjectBD() {
   // }
    @JsonCreator
    public ObjectBD(@JsonProperty("id") int id,@JsonProperty("objName") String objName,
                    @JsonProperty("objDesc") String objDesc,@JsonProperty("parendId") int parentId,
                    @JsonProperty("objectType") Integer objectType) {
        this.id = id;
        this.objName = objName;
        this.objDesc = objDesc;
        this.parentId = parentId;
        this.objectType = objectType;
    }

    public ObjectBD(HashMap<String, Object> e) throws Exception {
        if(!e.containsKey("id"))
            throw new Exception();
        id = Integer.valueOf(e.get("id").toString());
        if(e.containsKey("objName") && e.get("objName") != null)
            objName = e.get("objName").toString();
        else
            objName = "";
        if(e.containsKey("objDesc")&& e.get("objDesc") != null)
            objDesc = e.get("objDesc").toString();
        else
            objDesc = "";
        if(e.containsKey("parentId")&& e.get("parentId") != null)
            parentId = Integer.valueOf(e.get("parentId").toString());
        else
            parentId = 0;
        if(e.containsKey("objectType")&& e.get("objectType") != null)
            objectType = Integer.valueOf(e.get("objectType").toString());
        else
            objectType = 0;
    }

    public String getObjDesc() {

        return objDesc;
    }

    public void setObjDesc(String objDesc) {
        this.objDesc = objDesc;
    }

    public String getObjName() {
        return objName;
    }
    public int getId() {
        return id;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {

        this.parentId = parentId <=0?null:parentId;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public String getObjectClass()
    {
        return "general";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        ObjectBD objectBD = (ObjectBD) o;

        return id == objectBD.id;
    }

    public String getHtmlUl()
    {
        return id+"|"+publicName()+"|"+objDesc+"|"+objectType+"|"+parentId;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String publicName() {
        return objName;
    }
}

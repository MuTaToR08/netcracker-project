package com.netcraker.project.bd.shared.objects;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectBD implements Named{

    protected int id;
    private String objName;
    private String objDesc;
    private Integer parentId;
    private int objectType;

    public ObjectBD(@JsonProperty("id") int id) {
        this.id = id;
        parentId = null;
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
    public String publicName() {
        return objName;
    }
}

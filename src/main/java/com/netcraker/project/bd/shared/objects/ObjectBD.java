package com.netcraker.project.bd.shared.objects;


public class ObjectBD implements Named{
    private String objName;
    private int parentId;
    private int objectType;

    public ObjectBD() {
    }

    String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getObjectType() {
        return objectType;
    }

    public void setObjectType(int objectType) {
        this.objectType = objectType;
    }

    public String getCssClass()
    {
        return "general";
    }

    @Override
    public String publicName() {
        return objName;
    }
}

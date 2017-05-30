package com.netcraker.project.bd.shared.containers;

import com.netcraker.project.bd.shared.objects.ObjectBD;

public class TreeNode {
    private ObjectBD elemnt;
    private TreeNode[] children;
    private boolean visible;

    public boolean isVisible() {
        return visible;
    }


    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ObjectBD getElemnt() {
        return elemnt;

    }

    public TreeNode() {
        visible = true;
    }

    public TreeNode[] getChildren() {
        return children;
    }

    public void setElemnt(ObjectBD elemnt) {

        this.elemnt = elemnt;
    }

    public void setChildren(TreeNode[] children) {
        this.children = children;
    }

    public TreeNode(ObjectBD elemnt) {

        this.elemnt = elemnt;
    }
}

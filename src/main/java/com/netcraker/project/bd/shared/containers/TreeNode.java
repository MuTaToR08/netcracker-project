package com.netcraker.project.bd.shared.containers;

import com.netcraker.project.bd.shared.objects.ObjectBD;
import com.netcraker.project.bd.shared.objects.location.Building;

import java.util.ArrayList;
import java.util.List;


public class TreeNode {
        private ObjectBD elemnt;
    //private SimpleContainer<? super ObjectBD> elemnt;
    protected List<TreeNode> children = new ArrayList<>();
    private boolean visible;
    private boolean opened;

    public TreeNode(SimpleContainer<? super ObjectBD> add) {
        elemnt = add.getData();
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public boolean isVisible() {
        return visible;
    }


    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public ObjectBD getElemnt() {
        return elemnt;
    }

   /* public SimpleContainer<? super ObjectBD> getContainer() {
        return elemnt;
    }*/

    public TreeNode() {
        visible = true;
        opened = true;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setElemnt(ObjectBD elemnt) {
        this.elemnt =(elemnt);
    }

    /*public void setContainer(SimpleContainer<? super ObjectBD> cont){
        this.elemnt.setData(cont.getData());
}*/
    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
    public TreeNode addChildren(ObjectBD add)
    {
        TreeNode tmp = new TreeNode(add);
        if(!children.contains(tmp)){
            tmp = new TreeNode(add);
            children.add(tmp);
        }else
        {
            tmp = children.get(children.indexOf(tmp));
        }
        return tmp;
    }
    public TreeNode(ObjectBD elemnt) {

        this.elemnt = (elemnt);
        opened = false;
        visible = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode treeNode = (TreeNode) o;

        return elemnt != null ? elemnt.equals(treeNode.elemnt) : treeNode.elemnt == null;

    }

    @Override
    public int hashCode() {
        return elemnt != null ? elemnt.hashCode() : 0;
    }

    public void addChildren(SimpleContainer<? super ObjectBD> add) {
        if(!children.contains(add.getData()))
            children.add(new TreeNode(add));
    }
}

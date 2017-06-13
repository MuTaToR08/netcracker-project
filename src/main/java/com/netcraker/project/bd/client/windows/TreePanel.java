package com.netcraker.project.bd.client.windows;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.netcraker.project.bd.client.BD;
import com.netcraker.project.bd.client.FactoryView;
import com.netcraker.project.bd.client.api.TreeApi;
import com.netcraker.project.bd.client.buttons.AdditionButton;
import com.netcraker.project.bd.client.full.FullWindowPanel;
import com.netcraker.project.bd.shared.containers.TreeNode;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import com.netcraker.project.bd.shared.objects.controled.ExtendsButton;
import com.netcraker.project.bd.shared.objects.controled.FullWindow;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.HashMap;
import java.util.List;

public class TreePanel implements Panels {

    private final RootPanel main = RootPanel.get("main");
    private final Panel listCustomer = new FlowPanel();
    private final TreeNode tree = new TreeNode();
    private final HashMap<Integer,TreeNode> allObject = new HashMap<>();

    public void replaceWindows() {
        replaceWindows(main);
    }

    private void replaceWindows(RootPanel replaced) {
        replaced.clear();
    }

    public void makeupTree(TreeNode node,Panel panel)
    {

        FlowPanel ul = new FlowPanel();
        ul.setStyleName("tree-ul-li");
        int response = 0;
        for (TreeNode treeNode : node.getChildren()) {
            if(!treeNode.isVisible())
                continue;
            FlowPanel li = new FlowPanel();
            FlowPanel data = new FlowPanel();
            FlowPanel treeflow = new FlowPanel();
            FlowPanel flowPanel = null;
            Widget html = new HTML(treeNode.getElemnt().getHtmlUl());
            html.setStyleName("tree-"+treeNode.getElemnt().getObjectClass());
            html.addStyleName("tree-element");

            Button open = new Button("Open", new ClickHandler() {
                public void onClick(ClickEvent clickEvent) {
                    TreeApi treeApi = GWT.create(TreeApi.class);
                    final int id = Integer.valueOf(clickEvent.getRelativeElement().getAttribute("data-id"));
                    BD.viewPreloader();
                    treeApi.getChilds(id, new MethodCallback<List<Object>>() {
                        public void onFailure(Method method, Throwable throwable) {}

                        public void onSuccess(Method method, List<Object> objects) {
                            addToTree(id,objects);
                            hideNonClock(id,tree);
                            makeupTree();
                        }
                    });
                }
            });

            open.getElement().setAttribute("data-id",String.valueOf(treeNode.getElemnt().getId()));
            open.setStyleName("button-open");

            data.add(html);
            data.add(open);

            if(treeNode.getElemnt() instanceof FullWindow) {
                Button visib = new Button("visible", new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        FullWindowPanel fwp = FactoryView.getAddition(treeNode.getElemnt());
                        fwp.OpenWindow(treeNode.getElemnt());
                    }
                });
                visib.setStyleName("button-visible");
                data.add(visib);
            }

            if(treeNode.getElemnt() instanceof ExtendsButton) {
                flowPanel = new FlowPanel();
                flowPanel.getElement().setId("port_"+treeNode.getElemnt().getId());
                AdditionButton add = FactoryView.getAddition(treeNode.getElemnt(), this);
                if (add != null) {
                    add.extendsButton(flowPanel,treeNode.getElemnt());
                    data.add(flowPanel);
                }
            }

            li.add(data);
            data.setStyleName("info");
            treeflow.setStyleName("children");
            if(treeNode.getChildren().size() > 0)
            {
                li.add(treeflow);
                makeupTree(treeNode,treeflow);
            }
            ul.add(li);
        }
        panel.add(ul);
        BD.hidePreloader();
    }

    private Boolean hideNonClock(int id, TreeNode node) {
        Boolean ret = false;
        Boolean curRet = false;
        for (TreeNode treeNode : node.getChildren()) {
            ret = hideNonClock(id,treeNode);
            if(!ret && treeNode.getElemnt().getId()==id) {
                ret = true;
                for(TreeNode treeNode1 : treeNode.getChildren())
                    treeNode1.setVisible(true);
            }
            if(ret)
            {
                curRet = true;
                treeNode.setVisible(true);
            }
            else
                treeNode.setVisible(false);
        }
        return curRet;
    }

    void visible(int id,TreeNode node, Boolean set)
    {
        for(TreeNode treeNode : node.getChildren())
        {
            if(set)
            {
                treeNode.setVisible(true);
                visible(id,treeNode,set);
                continue;
            }
            if(treeNode.getElemnt().getId() != id)
                treeNode.setVisible(false);
            else
            {
                treeNode.setVisible(true);
                visible(id,treeNode,true);
            }
        }
    }

    public void makeupTree()
    {
        main.clear();
        makeupTree(tree,main);
    }

    void addToTree(int id,List<Object> objects)
    {
        for (Object object : objects) {
            ObjectBD tmp = FactoryView.createObject(object);
            if (tmp == null)
                continue;
            TreeNode node = addToNode(id,tmp);
            allObject.put(tmp.getId(),node);
        }
    }

    public void refreshData() {
        tree.getChildren().clear();
        allObject.clear();
        TreeApi treeApi = GWT.create(TreeApi.class);
        BD.viewPreloader();
        final int idGet = 0;

        treeApi.getChilds(idGet, new MethodCallback<List<Object>>() {
            public void onFailure(Method method, Throwable throwable) {}

            public void onSuccess(Method method, List<Object> objects) {
                addToTree(idGet,objects);
                makeupTree();
            }
        });
    }

    public void refreshData(int id) {
        final int idGet = id;
        tree.getChildren().clear();
        allObject.clear();
        TreeApi treeApi = GWT.create(TreeApi.class);
        BD.viewPreloader();
        treeApi.getParents(id, new MethodCallback<List<Object>>() {
            public void onFailure(Method method, Throwable throwable) {}

            public void onSuccess(Method method, List<Object> objects) {
                for (Object object : objects) {
                    ObjectBD tmp = FactoryView.createObject(object);
                    if (tmp == null)
                        continue;
                    TreeNode node = addToNode(tmp.getParentId(),tmp);
                    allObject.put(tmp.getId(),node);
                }
                makeupTree();
            }
        });
    }

    private void treeCreate(TreeNode tr)
    {}

    private  TreeNode addToNode(int idGet,ObjectBD tmp)
    {
        if(idGet == 0 || allObject.get(idGet) == null)
            return tree.addChildren(tmp);
        return allObject.get(idGet).addChildren(tmp);
    }

    public TreePanel() {}

    private void defaultWidgets() {}
}

package com.netcraker.project.bd.client.windows;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.builder.shared.UListBuilder;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.netcraker.project.bd.client.api.TreeApi;
import com.netcraker.project.bd.shared.containers.SimpleContainer;
import com.netcraker.project.bd.shared.containers.TreeNode;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import com.netcraker.project.bd.shared.objects.client.*;
import com.netcraker.project.bd.shared.objects.location.*;
import com.netcraker.project.bd.shared.objects.networked.Carrier;
import com.netcraker.project.bd.shared.objects.networked.Port;
import com.netcraker.project.bd.shared.objects.networked.Router;
import com.netcraker.project.bd.shared.objects.networked.Switch;
import org.apache.tapestry.wml.Do;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TreePanel implements Panels {
    private final RootPanel main = RootPanel.get("main");
    //  private final TreePanel customers = new TreePanel();
    private final Panel listCustomer = new FlowPanel();
    private final TreeNode tree = new TreeNode();
    private final HashMap<Integer,TreeNode> allObject = new HashMap<>();


    @Override
    public void replaceWindows() {
        replaceWindows(main);
    }

    private void replaceWindows(RootPanel replaced) {
        replaced.clear();
    }

    private ObjectBD createObject(Object e1) {
        HashMap<String, Object> e = (HashMap<String, Object>) e1;
        try {
            switch (Integer.valueOf(e.get("objectType").toString())) {
                case 1://Здание
                    return new Building(e);
                case 2://Этаж
                    return new Floor(e);
                case 3://Комната
                    return new Room(e);
                case 4://Стойка
                    return new Stand(e);
                case 15://Провод Carrier
                    return new Carrier(e);
                case 16://Комутатор
                    return new Switch(e);
                case 17://Маршрутизатор
                    return new Router(e);
                case 28://Порт
                    return new Port(e);
                case 29://Заказ
                    return new Order(e);
                case 30://Заказчик
                    return new Customer(e);
                case 31://Услуга(сервис)
                    return new Service(e);
                case 32://Тариф
                    return new Tariff(e);
                case 33://Оказание услуги(SCI)
                    return new CSI(e);
                case 34://Операции по счёту(billing)
                    return new Billing(e);
                case 35://Цена (TSP)
                    return new TSP(e);
                case 39://Хранилище
                    return new Stock(e);
                default:
                    Window.alert(e.get("objectType").toString());
            }
        } catch (Exception e2) {
        }
        return null;
    }

    public void makeupTree(TreeNode node,Panel panel)
    {
        FlowPanel ul = new FlowPanel();
        ul.setStyleName("tree-ul");
        for (TreeNode treeNode : node.getChildren()) {
            if(!treeNode.isVisible())
                continue;
            FlowPanel li = new FlowPanel();
            FlowPanel data = new FlowPanel();
            FlowPanel treeflow = new FlowPanel();
            ul.setStyleName("tree-ul-li");
            Widget html = new HTML(treeNode.getElemnt().getHtmlUl());
            html.setStyleName("tree-"+treeNode.getElemnt().getObjectClass());
            html.addStyleName("tree-element");
            html.getElement().setAttribute("data-id",String.valueOf(treeNode.getElemnt().getId()));
            html.addDomHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    TreeApi treeApi = GWT.create(TreeApi.class);
                    final int id = Integer.valueOf(clickEvent.getRelativeElement().getAttribute("data-id"));
                    treeApi.getChilds(id, new MethodCallback<List<Object>>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {

                        }

                        @Override
                        public void onSuccess(Method method, List<Object> objects) {
                            addToTree(id,objects);
                            hideNonClock(id,tree);
                            makeupTree();
                        }
                    });
                }
            }, ClickEvent.getType());

            data.add(html);
            data.add(new Button("visible", new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {

                }
            }));
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
    }

    private Boolean hideNonClock(int id, TreeNode node) {
        Boolean ret = false;
        Boolean curRet = false;
        for (TreeNode treeNode : node.getChildren()) {
            ret = hideNonClock(id,treeNode);
            if(!ret && treeNode.getElemnt().getId()==id) {
                ret = true;
                for(TreeNode treeNode1 : treeNode.getChildren())
                {
                    treeNode1.setVisible(true);
                }
            }
            if(ret)
            {
                curRet = true;
                treeNode.setVisible(true);

            }
            else
            {
                treeNode.setVisible(false);
            }
        }
        /*for (TreeNode treeNode : node.getChildren()){

            if(curRet == treeNode.getElemnt().getId()) {
                visible(id, treeNode, true);
                treeNode.setVisible(true);
            }
            else {
                treeNode.setVisible(false);
                visible(id, treeNode, false);
            }
        }*/
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
            {
                treeNode.setVisible(false);
            }else
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
            ObjectBD tmp = createObject(object);
            if (tmp == null)
                continue;
            TreeNode node = addToNode(id,tmp);
            allObject.put(tmp.getId(),node);
        }
    }

    @Override
    public void refreshData() {
        tree.getChildren().clear();
        allObject.clear();
        TreeApi treeApi = GWT.create(TreeApi.class);
        final int idGet = 0;
        treeApi.getChilds(idGet, new MethodCallback<List<Object>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<Object> objects) {
                addToTree(idGet,objects);
                makeupTree();
            }
        });
    }
    private  TreeNode addToNode(int idGet,ObjectBD tmp)
    {
        if(idGet == 0)
            return tree.addChildren(tmp);

        return allObject.get(idGet).addChildren(tmp);
    }

    public TreePanel() {

    }

    private void defaultWidgets() {

    }


}

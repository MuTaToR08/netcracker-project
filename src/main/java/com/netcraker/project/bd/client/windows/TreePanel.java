package com.netcraker.project.bd.client.windows;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.IntegerBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
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

    private ObjectBD createObject(HashMap<String, Object> e) {
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
            }
        } catch (Exception e1) {
        }
        return null;
    }

    @Override
    public void refreshData() {

        TreeApi treeApi = GWT.create(TreeApi.class);
        treeApi.getChilds(0, new MethodCallback<List<Object>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<Object> objects) {
                for (Object object : objects) {
                    /*Window.alert*/
                    HashMap<String, Object> e = (HashMap<String, Object>) object;
                    ObjectBD tmp = createObject(e);
                    if (tmp == null)
                        continue;
                    allObject.put(tmp.getId(),tree.addChildren(tmp));
//                    Window.alert(e.get("objectType").getClass().getName());

                }
                for (int i = 0; i < tree.getChildren().size(); i++) {
                    Window.alert((tree.getChildren().get(i).getElemnt()).toString());
                }
                /*List<TreeNode> child = tree.getChildren();


                for (int i = 0; i < tree.getChildren().size(); i++) {
                    Window.alert(tree.getChildren().get(i).getElemnt().toString());
                }*/

                //Window.alert(objects.toString());
            }
        });
        //Window.alert(list.toString());
/*
        treeApi.getChilds(0, new MethodCallback<List<? super ObjectBD>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, List<? super ObjectBD> objects) {
                for (Object object : objects) {
                    Window.alert(object.toString());
                }
            }
        });*/
    }

    public TreePanel() {

    }

    private void defaultWidgets() {

    }


}

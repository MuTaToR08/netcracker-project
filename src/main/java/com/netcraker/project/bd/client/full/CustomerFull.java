package com.netcraker.project.bd.client.full;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dev.json.JsonObject;
import com.google.gwt.dev.json.JsonValue;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.netcraker.project.bd.client.BD;
import com.netcraker.project.bd.client.api.TreeApi;
import com.netcraker.project.bd.client.api.client.TariffApi;
import com.netcraker.project.bd.shared.TypeName;
import com.netcraker.project.bd.shared.containers.TreeList;
import com.netcraker.project.bd.shared.containers.TreeNode;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import com.netcraker.project.bd.shared.objects.client.Customer;
import com.netcraker.project.bd.shared.objects.client.Tariff;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.netcraker.project.bd.client.FactoryView.createObject;

/**
 * Created by mutat on 08.06.2017.
 */
public class CustomerFull implements FullWindowPanel<Customer> {
    @Override
    public void OpenWindow(Customer obj) {
        BD.viewPreloader();
        TreeApi path = GWT.create(TreeApi.class);
        final int[] query = {0,0};
        Panel breads =  RootPanel.get("breadcrumbs");
        breads.clear();
        path.getParents(obj.getId(), new MethodCallback<List<Object>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {}
            @Override
            public void onSuccess(Method method, List<Object> objects) {
                /*List<ObjectBD> obj = new ArrayList<>();*/

                Element bs = (RootPanel.get("breadcrumbs").getElement());
                for (Object object : objects) {
                    ObjectBD tmp = createObject(object);
                    if (tmp == null)
                        continue;
                    bs.setInnerHTML(bs.getInnerHTML()+"<li>"+tmp.publicName()+"</li>");
                }
                query[0]++;
            }
        });
        RootPanel main = RootPanel.get("main");
        main.clear();
        FlowPanel left = new FlowPanel();
        FlowPanel right = new FlowPanel();
        HorizontalPanel tbl = new HorizontalPanel();
        main.add(tbl);
        tbl.setWidth("100%");
        tbl.add(left);
        tbl.add(right);
        left.add(addLabelBox("ФИО:"     ,"fio"      ,obj.getFio()                   ) );
        left.add(addLabelBox("ИНН:"     ,"inn"      ,String.valueOf(obj.getInn())   ) );
        left.add(addLabelBox("Телефон:" ,"phone"    ,String.valueOf(obj.getPhone()) ) );
        left.add(addLabelBox("Логин:"   ,"login"    ,obj.getUsername()              ) );
        left.add(addLabelBox("Пароль:"  ,"pass"     ,obj.getPassword()              ) );
        FlowPanel tarif = addLabelBox("Тариф:"   ,"tariff"   ,obj.getTariff().getTariffName());
        left.add(tarif);
        //left.add(addLabelBox("Тариф:"   ,"tariff"   ,obj.getTariff().getTariffName()) );
        left.add(addLabelBox("Статус:"   ,"status"  ,BD.getStatuses().get(obj.getStatus()).getText()) );
        left.add(addLabelBox("Баланс(руб):"   ,"balance" ,String.valueOf(obj.getBalance())) );

        TariffApi tariffApi = GWT.create(TariffApi.class);
        tariffApi.getAll(new MethodCallback<Map<Integer, Tariff>>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, Map<Integer, Tariff> tariffs) {
                tarif.clear();
                InlineLabel label = new InlineLabel("Тариф:");
                label.setStyleName("labelCustomer");
                tarif.add(label);
                ListBox tariffList = new ListBox();
                for (Map.Entry<Integer, Tariff> entry : tariffs.entrySet()) {
                    tariffList.addItem(entry.getValue().getObjName(),String.valueOf(entry.getKey()));
                }
                tarif.add(tariffList);

            }
        });



        Timer t= new Timer() {
            @Override
            public void run() {
                if(query[0] == 1) {
                    BD.hidePreloader();
                    return;
                }
                schedule(500);
            }
        };
        t.schedule(500);
    }

    private FlowPanel addLabelBox(String Label, String name, String value)
    {
        InlineLabel label = new InlineLabel(Label);
        label.setStyleName("labelCustomer");
        TextBox strBox = new TextBox();
        strBox.setName(name);
        strBox.setValue(value);
        FlowPanel str = new FlowPanel();
        str.add(label);
        str.add(strBox);
        return str;
    }

}
